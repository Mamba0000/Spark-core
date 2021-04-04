package com.lyc.spark.core.auth.aop;

import com.lyc.spark.core.auth.annotation.PreAuth;
import com.lyc.spark.core.auth.util.BladeUser;
import com.lyc.spark.core.auth.util.SecureUtil;
import com.lyc.spark.core.common.exception.TokenException;
import com.lyc.spark.core.redis.util.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 自定义权限验证
 *
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class PreAuthAspect {

	/**
	 * 所有权限标识
	 */
	private static final String ALL_PERMISSION = "*:*:*";

	private  HttpServletRequest request;

//	private  RedisUtil redisService;


	public PreAuthAspect() {

	}

	@Around("@annotation(com.lyc.spark.core.auth.annotation.PreAuth)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		PreAuth preAuth = method.getAnnotation(PreAuth.class);
		if (ObjectUtils.isEmpty(preAuth)) {
			return point.proceed();
		}
		if (hasPerm(preAuth.hasPerm())) {
			return point.proceed();
		} else {
			throw new TokenException("权限验证不通过");
		}
	}


	/**
	 * 验证用户是否具备某权限
	 *
	 * @param permission 权限字符串
	 * @return 用户是否具备某权限
	 */
	public boolean hasPerm(String permission) {
//		return  true;

//		LoginUser userInfo = SecurityUtil.getUsername(request);

		BladeUser mBladeUser = SecureUtil.getUser();
		if (StringUtils.isEmpty(mBladeUser)) {
			return false;
		}
		// 如果已经登录 并且不需要 permission
		if (!StringUtils.isEmpty(mBladeUser) && StringUtils.isEmpty(permission)) {
			return true;
		}

//		// 如果用户是超级管理员，则直接跳过权限验证
//		if (userInfo.getAccount().equalsIgnoreCase(Oauth2Constant.SUPER_ADMIN)) {
//			return true;
//		}

		List<String> authorities = Arrays.asList(SecureUtil.getUserPermissions().split("\\$"));
		return hasPermissions(authorities, permission);
	}

	/**
	 * 判断是否包含权限
	 *
	 * @param authorities 权限列表
	 * @param permission  权限字符串
	 * @return 用户是否具备某权限
	 */
	private boolean hasPermissions(Collection<String> authorities, String permission) {
		return authorities.stream().filter(StringUtils::hasText)
				.anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(permission, x));
	}
}
