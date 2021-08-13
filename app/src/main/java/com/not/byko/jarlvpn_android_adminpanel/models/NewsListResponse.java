package com.not.byko.jarlvpn_android_adminpanel.models;

public class NewsListResponse {

    public String newsContent;
    public String newsDate;
    public String newsId;

    public NewsListResponse(String newsContent, String newsDate, String newsId) {
        this.newsContent = newsContent;
        this.newsDate = newsDate;
        this.newsId = newsId;
    }

    public NewsListResponse(){}

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
