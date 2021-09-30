package com.not.byko.jarlvpn_android_adminpanel.models;

import java.util.List;

public class AvailableRegions {

    public List<String> city;
    public List<String> country;

    public AvailableRegions(List<String> city, List<String> country) {
        this.city = city;
        this.country = country;
    }
    public AvailableRegions(){}

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }
}
