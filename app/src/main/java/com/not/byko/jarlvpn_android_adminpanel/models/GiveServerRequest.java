package com.not.byko.jarlvpn_android_adminpanel.models;

public class GiveServerRequest {

    public String city;
    public Integer months;
    public String username;

    public GiveServerRequest(String city, Integer months, String username) {
        this.city = city;
        this.months = months;
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
