package com.not.byko.jarlvpn_android_adminpanel.models;

public class ConfigRequest {

    public String usernameId;
    public String configName;

    public ConfigRequest(String usernameId, String configName) {
        this.usernameId = usernameId;
        this.configName = configName;
    }

    public String getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(String usernameId) {
        this.usernameId = usernameId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
