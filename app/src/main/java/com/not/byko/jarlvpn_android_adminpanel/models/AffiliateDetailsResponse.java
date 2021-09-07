package com.not.byko.jarlvpn_android_adminpanel.models;

public class AffiliateDetailsResponse {

    public String code;
    public Integer usedTime;
    public Long totalPrice;
    public Long withdrawPrice;

    public AffiliateDetailsResponse(String code, Integer usedTime, Long totalPrice, Long withdrawPrice) {
        this.code = code;
        this.usedTime = usedTime;
        this.totalPrice = totalPrice;
        this.withdrawPrice = withdrawPrice;
    }

    public AffiliateDetailsResponse() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getWithdrawPrice() {
        return withdrawPrice;
    }

    public void setWithdrawPrice(Long withdrawPrice) {
        this.withdrawPrice = withdrawPrice;
    }
}
