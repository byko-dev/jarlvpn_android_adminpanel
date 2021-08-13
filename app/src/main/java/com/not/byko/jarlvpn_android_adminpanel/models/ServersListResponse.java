package com.not.byko.jarlvpn_android_adminpanel.models;

public class ServersListResponse {

    private String id;
    private String ipAddress;
    private String createDate;
    private String expDate;
    private String userExpDate;
    private String serverCity;
    private String serverCountry;
    private String hosting;
    private String ownerEmail;
    private String passphrase;

    public ServersListResponse(String id, String ipAddress, String createDate, String expDate,
                               String userExpDate, String serverCity, String serverCountry,
                               String hosting, String ownerEmail, String passphrase) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.createDate = createDate;
        this.expDate = expDate;
        this.userExpDate = userExpDate;
        this.serverCity = serverCity;
        this.serverCountry = serverCountry;
        this.hosting = hosting;
        this.ownerEmail = ownerEmail;
        this.passphrase = passphrase;
    }

    public ServersListResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getUserExpDate() {
        return userExpDate;
    }

    public void setUserExpDate(String userExpDate) {
        this.userExpDate = userExpDate;
    }

    public String getServerCity() {
        return serverCity;
    }

    public void setServerCity(String serverCity) {
        this.serverCity = serverCity;
    }

    public String getServerCountry() {
        return serverCountry;
    }

    public void setServerCountry(String serverCountry) {
        this.serverCountry = serverCountry;
    }

    public String getHosting() {
        return hosting;
    }

    public void setHosting(String hosting) {
        this.hosting = hosting;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }
}
