package com.zyj010.huaba.model.http;

/**
 * Created by runzii on 16-6-3.
 */
public class RegistBody {


    /**
     * avatar : string
     * phone : string
     * role : ROLE_ADMIN
     * username : string
     */

    private String passwrod;
    private String phone;
    private String role;
    private String username;

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
