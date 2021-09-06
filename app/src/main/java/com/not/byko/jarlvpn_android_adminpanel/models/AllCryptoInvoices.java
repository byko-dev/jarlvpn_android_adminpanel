package com.not.byko.jarlvpn_android_adminpanel.models;

public class AllCryptoInvoices {

    public String idTransaction;
    public String ownerMail;
    public String purchaseDate;
    public boolean paid;
    public float price;
    public String cryptocurrency;
    public String subType;

    public AllCryptoInvoices(String idTransaction, String ownerMail, String purchaseDate,
                             boolean paid, float price, String cryptocurrency, String subType) {
        this.idTransaction = idTransaction;
        this.ownerMail = ownerMail;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
        this.price = price;
        this.cryptocurrency = cryptocurrency;
        this.subType = subType;
    }

    public AllCryptoInvoices() {}

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

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

    public String getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(String cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
