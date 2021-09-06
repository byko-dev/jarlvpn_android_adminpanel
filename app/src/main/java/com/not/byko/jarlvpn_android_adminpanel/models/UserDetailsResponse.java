package com.not.byko.jarlvpn_android_adminpanel.models;

import java.util.List;

public class UserDetailsResponse {

    public String createDate;
    public Boolean vpnActivated;
    public List<String> ipAddress;
    public String userType;
    public Boolean affiliatePartner;

    public UserDetailsResponse(String createDate, Boolean vpnActivated, List<String> ipAddress,
                               String userType, Boolean affiliatePartner) {
        this.createDate = createDate;
        this.vpnActivated = vpnActivated;
        this.ipAddress = ipAddress;
        this.userType = userType;
        this.affiliatePartner = affiliatePartner;
    }

    public UserDetailsResponse() {}

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Boolean getVpnActivated() {
        return vpnActivated;
    }

    public void setVpnActivated(Boolean vpnActivated) {
        this.vpnActivated = vpnActivated;
    }

    public List<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getAffiliatePartner() {
        return affiliatePartner;
    }

    public void setAffiliatePartner(Boolean affiliatePartner) {
        this.affiliatePartner = affiliatePartner;
    }
}
