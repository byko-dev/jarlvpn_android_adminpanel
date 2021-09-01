package com.not.byko.jarlvpn_android_adminpanel.models;

public class RemoveServerRequest {

    public String serverID;
    public Boolean wipeClientConfig;

    public RemoveServerRequest(String serverID, Boolean wipeClientConfig) {
        this.serverID = serverID;
        this.wipeClientConfig = wipeClientConfig;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public Boolean getWipeClientConfig() {
        return wipeClientConfig;
    }

    public void setWipeClientConfig(Boolean wipeClientConfig) {
        this.wipeClientConfig = wipeClientConfig;
    }
}
