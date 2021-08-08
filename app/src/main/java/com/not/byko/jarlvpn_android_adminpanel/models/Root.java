package com.not.byko.jarlvpn_android_adminpanel.models;

import java.util.List;

public class Root {

    public List<DiscountCode> discountCodeList;


    public Root(List<DiscountCode> discountCodeList) {
        this.discountCodeList = discountCodeList;
    }

    public List<DiscountCode> getDiscountCodeList() {
        return discountCodeList;
    }

    public void setDiscountCodeList(List<DiscountCode> discountCodeList) {
        this.discountCodeList = discountCodeList;
    }
}
