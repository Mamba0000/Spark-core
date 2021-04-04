package com.lyc.spark.core.web.handler;

import com.lyc.spark.core.common.api.CommonResult;
import com.lyc.spark.core.common.exception.BaseException;
import com.lyc.spark.core.common.exception.PreviewException;
import com.lyc.spark.core.common.exception.TokenException;
import com.lyc.spark.core.tool.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;

/**
 * Springboot WEB应用全局异常处理
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * BaseException 异常捕获处理
     * @param ex 自定义BaseException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<?> handleException(BaseException ex) {
        log.error("程序异常：" + ex.toString());
        return CommonResult.fail(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    /**
     * TokenException 异常捕获处理
     * @param ex 自定义TokenException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<?> handleException(TokenException ex) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return CommonResult.fail(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    /**
     * FileNotFoundException,NoHandlerFoundException 异常捕获处理
     * @param exception 自定义FileNotFoundException异常类型
     * @return Result
     */
    @ExceptionHandler({FileNotFoundException.class, NoHandlerFoundException.class})
    public CommonResult<?> noFoundException(Exception exception) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return CommonResult.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    /**
     * PreviewException 空指针异常捕获处理
     * @param ex 自定义PreviewException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<?> handleException(PreviewException ex) {
        log.error("程序异常：" + ex.toString());
        return CommonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * NullPointerException 空指针异常捕获处理
     * @param ex 自定义NullPointerException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<?> handleException(NullPointerException ex) {
        log.error("程序异常：{}" + ex.toString());
        return CommonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    /**
     * NullPointerException 空指针异常捕获处理
     * @param ex 自定义NullPointerException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult<?> handleException(AccessDeniedException ex) {
        log.error("程序异常：{}" + ex.toString());
        return CommonResult.fail(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }


    /**
     * 通用Exception异常捕获
     * @param ex 自定义Exception异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<?> handleException(Exception ex) {
        log.error("程序异常：" + ex.toString());
        String message = ex.getMessage();
//        if (StringUtil.contains(message, "Bad credentials")){
//            message = "您输入的密码不正确";
//        } else if (StringUtil.contains(ex.toString(), "InternalAuthenticationServiceException")) {
//            message = "您输入的用户名不存在";
//        }
        return CommonResult.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
