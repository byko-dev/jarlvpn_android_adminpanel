package com.not.byko.jarlvpn_android_adminpanel.models;

public class StatusModel {

    public String message;

    public StatusModel(String message) {
        this.message = message;
    }

    public StatusModel(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
