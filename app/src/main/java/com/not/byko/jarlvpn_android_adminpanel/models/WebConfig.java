package com.not.byko.jarlvpn_android_adminpanel.models;

public class WebConfig {

    public Float oneMonthPrice;
    public Integer sixMonthDiscount;
    public Integer totalUsers;
    public Integer totalServers;
    public Integer totalPaypalInvoices;
    public Integer totalCryptoInvoices;
    public Integer unusedServers;
    public Integer successInvoicesPaypal;
    public Integer successInvoicesCrypto;
    public Integer todayUsersRegistered;
    public Integer todayPaypalInvoices;
    public Integer todayCryptoInvoices;

    public WebConfig(Float oneMonthPrice, Integer sixMonthDiscount, Integer totalUsers,
                     Integer totalServers, Integer totalPaypalInvoices, Integer totalCryptoInvoices,
                     Integer unusedServers, Integer successInvoicesPaypal, Integer successInvoicesCrypto,
                     Integer todayUsersRegistered, Integer todayPaypalInvoices, Integer todayCryptoInvoices) {
        this.oneMonthPrice = oneMonthPrice;
        this.sixMonthDiscount = sixMonthDiscount;
        this.totalUsers = totalUsers;
        this.totalServers = totalServers;
        this.totalPaypalInvoices = totalPaypalInvoices;
        this.totalCryptoInvoices = totalCryptoInvoices;
        this.unusedServers = unusedServers;
        this.successInvoicesPaypal = successInvoicesPaypal;
        this.successInvoicesCrypto = successInvoicesCrypto;
        this.todayUsersRegistered = todayUsersRegistered;
        this.todayPaypalInvoices = todayPaypalInvoices;
        this.todayCryptoInvoices = todayCryptoInvoices;
    }

    public WebConfig(){}

    public Float getOneMonthPrice() {
        return oneMonthPrice;
    }

    public void setOneMonthPrice(Float oneMonthPrice) {
        this.oneMonthPrice = oneMonthPrice;
    }

    public Integer getSixMonthDiscount() {
        return sixMonthDiscount;
    }

    public void setSixMonthDiscount(Integer sixMonthDiscount) {
        this.sixMonthDiscount = sixMonthDiscount;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Integer getTotalServers() {
        return totalServers;
    }

    public void setTotalServers(Integer totalServers) {
        this.totalServers = totalServers;
    }

    public Integer getTotalPaypalInvoices() {
        return totalPaypalInvoices;
    }

    public void setTotalPaypalInvoices(Integer totalPaypalInvoices) {
        this.totalPaypalInvoices = totalPaypalInvoices;
    }

    public Integer getTotalCryptoInvoices() {
        return totalCryptoInvoices;
    }

    public void setTotalCryptoInvoices(Integer totalCryptoInvoices) {
        this.totalCryptoInvoices = totalCryptoInvoices;
    }

    public Integer getUnusedServers() {
        return unusedServers;
    }

    public void setUnusedServers(Integer unusedServers) {
        this.unusedServers = unusedServers;
    }

    public Integer getSuccessInvoicesPaypal() {
        return successInvoicesPaypal;
    }

    public void setSuccessInvoicesPaypal(Integer successInvoicesPaypal) {
        this.successInvoicesPaypal = successInvoicesPaypal;
    }

    public Integer getSuccessInvoicesCrypto() {
        return successInvoicesCrypto;
    }

    public void setSuccessInvoicesCrypto(Integer successInvoicesCrypto) {
        this.successInvoicesCrypto = successInvoicesCrypto;
    }

    public Integer getTodayUsersRegistered() {
        return todayUsersRegistered;
    }

    public void setTodayUsersRegistered(Integer todayUsersRegistered) {
        this.todayUsersRegistered = todayUsersRegistered;
    }

    public Integer getTodayPaypalInvoices() {
        return todayPaypalInvoices;
    }

    public void setTodayPaypalInvoices(Integer todayPaypalInvoices) {
        this.todayPaypalInvoices = todayPaypalInvoices;
    }

    public Integer getTodayCryptoInvoices() {
        return todayCryptoInvoices;
    }

    public void setTodayCryptoInvoices(Integer todayCryptoInvoices) {
        this.todayCryptoInvoices = todayCryptoInvoices;
    }
}
