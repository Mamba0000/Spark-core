package com.lyc.spark.core.auth.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.lyc.spark.core.auth.exception.SecureException;
import com.lyc.spark.core.auth.info.TokenInfo;
import com.lyc.spark.core.common.constant.TokenConstant;
import com.lyc.spark.core.tool.Charsets;
import com.lyc.spark.core.tool.Func;
import com.lyc.spark.core.tool.StringUtil;
import com.lyc.spark.core.tool.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;

public class SecureUtil {
    private static final String BLADE_USER_REQUEST_ATTR = "_BLADE_USER_REQUEST_ATTR_";
    private static final String HEADER =  TokenConstant.HEADER; //"blade-auth";
    private static final String BEARER =  TokenConstant.BEARER; //"bearer";
    private static final String ACCOUNT =  TokenConstant.ACCOUNT; //"account";
    private static final String USER_ID = TokenConstant.USER_ID;  //"user_id";
    private static final String ROLE_ID = TokenConstant.ROLE_ID; //"role_id";
    private static final String USER_NAME = TokenConstant.USER_NAME; //"user_name";
    private static final String ROLE_NAME = TokenConstant.ROLE_NAME; //"role_name";
    private static final String TENANT_ID = TokenConstant.TENANT_ID; //"tenant_id";
    private static final String CLIENT_ID = TokenConstant.CLIENT_ID;
    private static final String PERMISSIONS = TokenConstant.PERMISSIONS;
    private static final Integer AUTH_LENGTH;
    private static final String BASE64_SECURITY;
//    private static final IClientDetailsService clientDetailsService;

    public SecureUtil() {
    }

    public static BladeUser getUser() {
        HttpServletRequest request = WebUtil.getRequest();
        if (request == null) {
            return null;
        } else {
            Object bladeUser = request.getAttribute(SecureUtil.BLADE_USER_REQUEST_ATTR);
            if (bladeUser == null) {
                bladeUser = getUser(request);
                if (bladeUser != null) {
                    request.setAttribute(SecureUtil.BLADE_USER_REQUEST_ATTR, bladeUser);
                }
            }

            return (BladeUser)bladeUser;
        }
    }

    public static BladeUser getUser(HttpServletRequest request) {
        Claims claims = getClaims(request);
        if (claims == null) {
            return null;
        } else {
            String clientId = Func.toStr(claims.get(CLIENT_ID));
            Long userId = Func.toLong(claims.get(USER_ID));
            String tenantId = Func.toStr(claims.get(TENANT_ID));
            String roleId = Func.toStr(claims.get(ROLE_ID));
            String account = Func.toStr(claims.get(ACCOUNT));
            String roleName = Func.toStr(claims.get(ROLE_NAME));
            String userName = Func.toStr(claims.get(USER_NAME));
            String permissions = Func.toStr(claims.get(PERMISSIONS));

            BladeUser bladeUser = new BladeUser();
            bladeUser.setClientId(clientId);
            bladeUser.setUserId(userId);
            bladeUser.setTenantId(tenantId);
            bladeUser.setAccount(account);
            bladeUser.setRoleId(roleId);
            bladeUser.setRoleName(roleName);
            bladeUser.setUserName(userName);
            bladeUser.setPermissions(permissions);

            return bladeUser;
        }
    }

    public static boolean isAdministrator() {
        return StringUtil.containsAny(getUserRole(), new CharSequence[]{"administrator"});
    }

    public static Long getUserId() {
        BladeUser user = getUser();
        return null == user ? -1L : user.getUserId();
    }

    public static Long getUserId(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? -1L : user.getUserId();
    }

    public static String getUserAccount() {
        BladeUser user = getUser();
        return null == user ? "" : user.getAccount();
    }

    public static String getUserAccount(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getAccount();
    }

    public static String getUserName() {
        BladeUser user = getUser();
        return null == user ? "" : user.getUserName();
    }

    public static String getUserName(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getUserName();
    }

    public static String getUserRole() {
        BladeUser user = getUser();
        return null == user ? "" : user.getRoleName();
    }

    public static String getUserRole(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getRoleName();
    }

    public static String getUserPermissions() {
        BladeUser user = getUser();
        return null == user ? "" : user.getPermissions();
    }

    public static String getUserPermissions(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getPermissions();
    }

    public static String getTenantId() {
        BladeUser user = getUser();
        return null == user ? "" : user.getTenantId();
    }

    public static String getTenantId(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getTenantId();
    }

    public static String getClientId() {
        BladeUser user = getUser();
        return null == user ? "" : user.getClientId();
    }

    public static String getClientId(HttpServletRequest request) {
        BladeUser user = getUser(request);
        return null == user ? "" : user.getClientId();
    }

    public static Claims getClaims(HttpServletRequest request) {
        String auth = request.getHeader("blade-auth");
        String parameter;
        if (StringUtil.isNotBlank(auth) && auth.length() > AUTH_LENGTH) {
            parameter = auth.substring(0, 6).toLowerCase();
            if (parameter.compareTo("bearer") == 0) {
                auth = auth.substring(7);
                return parseJWT(auth);
            }
        } else {
            parameter = request.getParameter("blade-auth");
            if (StringUtil.isNotBlank(parameter)) {
                return parseJWT(parameter);
            }
        }

        return null;
    }

    public static String getHeader() {
        return getHeader((HttpServletRequest)Objects.requireNonNull(WebUtil.getRequest()));
    }

    public static String getHeader(HttpServletRequest request) {
        return request.getHeader("blade-auth");
    }

    public static Claims parseJWT(String jsonWebToken) {
        try {
            return (Claims)Jwts.parserBuilder().setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY)).build().parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception var2) {
            return null;
        }
    }

    public static TokenInfo createJWT(Map<String, String> user, String audience, String issuer, String tokenType) {
        // 目前不做 客户端 限制
//        String[] tokens = extractAndDecodeHeader();

//        assert tokens.length == 2;

//        String clientId = tokens[0];
//        String clientSecret = tokens[1];
//        IClientDetails clientDetails = clientDetails(clientId);
//        if (!validateClient(clientDetails, clientId, clientSecret)) {
//            throw new SecureException("客户端认证失败!");
//        } else {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").setIssuer(issuer).setAudience(audience).signWith(signingKey);
            user.forEach(builder::claim);
//            builder.claim("client_id", clientId);
            long expireMillis;
            if (tokenType.equals("access_token")) {
//                expireMillis = (long)(clientDetails.getAccessTokenValidity() * 1000);
                expireMillis = 5 * 24  * 60 * 60 * 1000;
            } else if (tokenType.equals("refresh_token")) {
//                expireMillis = (long)(clientDetails.getRefreshTokenValidity() * 1000);
                expireMillis = 5 * 24  * 60 * 60 * 1000;
            } else {
                expireMillis = getExpire();
            }

            long expMillis = nowMillis + expireMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setToken(builder.compact());
            tokenInfo.setExpire((int)expireMillis / 1000);
            return tokenInfo;
//        }
    }

    public static long getExpire() {
        Calendar cal = Calendar.getInstance();
        cal.add(6, 1);
        cal.set(11, 3);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }

//    public static String[] extractAndDecodeHeader() {
//        try {
//            String header = ((HttpServletRequest)Objects.requireNonNull(WebUtil.getRequest())).getHeader("Authorization");
//            header = Func.toStr(header).replace("Basic%20", "Basic ");
//            if (!header.startsWith("Basic ")) {
//                throw new SecureException("No client information in request header");
//            } else {
//                byte[] base64Token = header.substring(6).getBytes(Charsets.UTF_8_NAME);
//
//                byte[] decoded;
//                try {
//                    decoded = Base64.getDecoder().decode(base64Token);
//                } catch (IllegalArgumentException var5) {
//                    throw new RuntimeException("Failed to decode basic authentication token");
//                }
//
//                String token = new String(decoded, Charsets.UTF_8_NAME);
//                int index = token.indexOf(":");
//                if (index == -1) {
//                    throw new RuntimeException("Invalid basic authentication token");
//                } else {
//                    return new String[]{token.substring(0, index), token.substring(index + 1)};
//                }
//            }
//        } catch (Throwable var6) {
//            throw var6;
//        }
//    }

//    public static String getClientIdFromHeader() {
//        String[] tokens = extractAndDecodeHeader();
//
//        assert tokens.length == 2;
//
//        return tokens[0];
//    }

//    private static IClientDetails clientDetails(String clientId) {
//        return clientDetailsService.loadClientByClientId(clientId);
//    }

//    private static boolean validateClient(IClientDetails clientDetails, String clientId, String clientSecret) {
//        if (clientDetails == null) {
//            return false;
//        } else {
//            return StringUtil.equals(clientId, clientDetails.getClientId()) && StringUtil.equals(clientSecret, clientDetails.getClientSecret());
//        }
//    }

    static {
        AUTH_LENGTH = TokenConstant.AUTH_LENGTH;
        BASE64_SECURITY = Base64.getEncoder().encodeToString("bladexisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject".getBytes(Charsets.UTF_8));
//        clientDetailsService = (IClientDetailsService)SpringUtil.getBean(IClientDetailsService.class);
    }
}
