package com.lyc.spark.core.auth.info;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "认证信息"
)
public class AuthInfo {
    @ApiModelProperty("令牌")
    private String accessToken;
    @ApiModelProperty("令牌类型")
    private String tokenType;
    @ApiModelProperty("刷新令牌")
    private String refreshToken;
    @ApiModelProperty("用户ID")
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long userId;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("第三方系统ID")
    private String oauthId;
    @ApiModelProperty("头像")
    private String avatar = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
    @ApiModelProperty("角色名")
    private String authority;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("账号名")
    private String account;
    @ApiModelProperty("过期时间")
    private long expiresIn;
    @ApiModelProperty("许可证")
    private String license = "";

    public AuthInfo() {
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public String getOauthId() {
        return this.oauthId;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getAuthority() {
        return this.authority;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAccount() {
        return this.account;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public String getLicense() {
        return this.license;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setTenantId(final String tenantId) {
        this.tenantId = tenantId;
    }

    public void setOauthId(final String oauthId) {
        this.oauthId = oauthId;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setExpiresIn(final long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setLicense(final String license) {
        this.license = license;
    }
}
