package com.not.byko.jarlvpn_android_adminpanel.models;

public class CreateNewsRequest {

    public String newNews;

    public CreateNewsRequest(String newNews) {
        this.newNews = newNews;
    }
    public CreateNewsRequest(){}

    public String getNewNews() {
        return newNews;
    }

    public void setNewNews(String newNews) {
        this.newNews = newNews;
    }
}
