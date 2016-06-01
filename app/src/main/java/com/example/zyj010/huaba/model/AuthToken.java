package com.example.zyj010.huaba.model;

/**
 * Created by runzii on 16-4-13.
 */
public class AuthToken {


    /**
     * access_token : 02906c12-285e-417c-9485-6900b56d5f84
     * token_type : bearer
     * refresh_token : 6be7090b-3390-4125-9e69-cd5fd0faf700
     * expires_in : 3373397
     * scope : read write
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
