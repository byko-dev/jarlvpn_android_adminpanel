package com.not.byko.jarlvpn_android_adminpanel.models;

public class CreateDiscountCodeRequest {

    private String discountCodeName;
    private Integer discountPercentValue;
    private String discountPlan;
    private Integer discountBilling;

    public CreateDiscountCodeRequest(String discountCodeName, Integer discountPercentValue,
                                     String discountPlan, Integer discountBilling) {
        this.discountCodeName = discountCodeName;
        this.discountPercentValue = discountPercentValue;
        this.discountPlan = discountPlan;
        this.discountBilling = discountBilling;
    }

    public String getDiscountCodeName() {
        return discountCodeName;
    }

    public void setDiscountCodeName(String discountCodeName) {
        this.discountCodeName = discountCodeName;
    }

    public Integer getDiscountPercentValue() {
        return discountPercentValue;
    }

    public void setDiscountPercentValue(Integer discountPercentValue) {
        this.discountPercentValue = discountPercentValue;
    }

    public String getDiscountPlan() {
        return discountPlan;
    }

    public void setDiscountPlan(String discountPlan) {
        this.discountPlan = discountPlan;
    }

    public Integer getDiscountBilling() {
        return discountBilling;
    }

    public void setDiscountBilling(Integer discountBilling) {
        this.discountBilling = discountBilling;
    }
}
