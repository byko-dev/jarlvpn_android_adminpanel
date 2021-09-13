package com.not.byko.jarlvpn_android_adminpanel.models;

public class AffiliatePayments {

    public String username;
    public String purchaseDate;
    public Long days;
    public String idPayment;

    public AffiliatePayments(String username, String purchaseDate, Long days, String idPayment) {
        this.username = username;
        this.purchaseDate = purchaseDate;
        this.days = days;
        this.idPayment = idPayment;
    }

    public AffiliatePayments(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }
}
