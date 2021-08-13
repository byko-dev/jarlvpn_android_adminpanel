package com.not.byko.jarlvpn_android_adminpanel.models;

public class DiscountCode {

    public String Id;
    public String code;
    public Integer percentage;
    public Integer usedTimes;
    public String plan;
    public Integer billing;
    public String ownerId;

    public DiscountCode(){}

    public DiscountCode(String id, String code, Integer percentage, Integer usedTimes, String plan, Integer billing, String ownerId) {
        Id = id;
        this.code = code;
        this.percentage = percentage;
        this.usedTimes = usedTimes;
        this.plan = plan;
        this.billing = billing;
        this.ownerId = ownerId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Integer usedTimes) {
        this.usedTimes = usedTimes;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Integer getBilling() {
        return billing;
    }

    public void setBilling(Integer billing) {
        this.billing = billing;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
