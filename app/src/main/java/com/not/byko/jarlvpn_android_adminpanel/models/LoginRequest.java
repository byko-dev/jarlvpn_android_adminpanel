package com.not.byko.jarlvpn_android_adminpanel.models;

public class LoginRequest {

    private String username;
    private String password;
    private String responseCaptacha;

    public LoginRequest(String username, String password, String responseCaptacha){
        this.username = username;
        this.password = password;
        this.responseCaptacha = responseCaptacha;
    }

    public LoginRequest(){}

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

    public String getResponseCaptacha() {
        return responseCaptacha;
    }

    public void setResponseCaptacha(String responseCaptacha) {
        this.responseCaptacha = responseCaptacha;
    }
}
