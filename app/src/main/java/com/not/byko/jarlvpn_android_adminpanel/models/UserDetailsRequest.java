package com.not.byko.jarlvpn_android_adminpanel.models;

public class UserDetailsRequest {

    public String username;

    public UserDetailsRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
