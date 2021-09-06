package com.not.byko.jarlvpn_android_adminpanel.models;

public class AllPaypalInvoices {

    public String ownerMail;
    public String purchaseDate;
    public boolean paid;
    public float price;
    public String paymentId;
    public String subType;

    public AllPaypalInvoices(String ownerMail, String purchaseDate, boolean paid, float price,
                             String paymentId, String subType) {
        this.ownerMail = ownerMail;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
        this.price = price;
        this.paymentId = paymentId;
        this.subType = subType;
    }

    public AllPaypalInvoices() {}

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
