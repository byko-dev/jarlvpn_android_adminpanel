package com.not.byko.jarlvpn_android_adminpanel.models;

public class Configs {

    public String serverIp;
    public String confName;
    public String orgConfName;
    public String ownerMail;
    public String ownerId;

    public Configs(String serverIp, String confName, String orgConfName, String ownerMail, String ownerId) {
        this.serverIp = serverIp;
        this.confName = confName;
        this.orgConfName = orgConfName;
        this.ownerMail = ownerMail;
        this.ownerId = ownerId;
    }

    public Configs(){}

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getOrgConfName() {
        return orgConfName;
    }

    public void setOrgConfName(String orgConfName) {
        this.orgConfName = orgConfName;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
