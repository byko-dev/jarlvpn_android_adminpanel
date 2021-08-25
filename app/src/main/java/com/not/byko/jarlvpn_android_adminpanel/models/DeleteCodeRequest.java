package com.not.byko.jarlvpn_android_adminpanel.models;

public class DeleteCodeRequest {

    public String code;

    public DeleteCodeRequest(String code) {
        this.code = code;
    }

    public DeleteCodeRequest(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
