package com.not.byko.jarlvpn_android_adminpanel.models;

public class SetAffiliateAccessRequest {

    public String username;
    public String code;

    public SetAffiliateAccessRequest(String username, String code) {
        this.username = username;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
