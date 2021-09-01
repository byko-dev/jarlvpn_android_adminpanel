package com.not.byko.jarlvpn_android_adminpanel.models;

public class DeleteNewsRequest {

    public String newsId;

    public DeleteNewsRequest(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
