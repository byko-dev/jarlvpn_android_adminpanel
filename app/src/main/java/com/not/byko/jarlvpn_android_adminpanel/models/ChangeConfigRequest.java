package com.not.byko.jarlvpn_android_adminpanel.models;

public class ChangeConfigRequest {
    public Float oneMonthPrice;
    public Integer sixMonthDiscount;

    public ChangeConfigRequest(Float oneMonthPrice, Integer sixMonthDiscount) {
        this.oneMonthPrice = oneMonthPrice;
        this.sixMonthDiscount = sixMonthDiscount;
    }

    public ChangeConfigRequest(){}

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
}
