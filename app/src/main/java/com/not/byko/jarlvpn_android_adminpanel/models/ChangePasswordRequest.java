package com.not.byko.jarlvpn_android_adminpanel.models;

public class ChangePasswordRequest {

    public String username;
    public String password;
    public String retypePassword;

    public ChangePasswordRequest(String username, String password, String retypePassword) {
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
