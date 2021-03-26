package com.lyc.spark.core.auth.info;


public class TokenInfo {
    private String token;
    private int expire;

    public TokenInfo() {
    }

    public String getToken() {
        return this.token;
    }

    public int getExpire() {
        return this.expire;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setExpire(final int expire) {
        this.expire = expire;
    }

}
