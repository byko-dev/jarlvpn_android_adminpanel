package com.not.byko.jarlvpn_android_adminpanel.models;

public class ResetServerRequest {

    public String ipAddress;

    public ResetServerRequest(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
