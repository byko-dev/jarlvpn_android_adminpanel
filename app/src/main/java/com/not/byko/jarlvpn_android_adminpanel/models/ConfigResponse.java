package com.not.byko.jarlvpn_android_adminpanel.models;

public class ConfigResponse {

    public String configName;
    public String configContext;

    public ConfigResponse(String configName, String configContext) {
        this.configName = configName;
        this.configContext = configContext;
    }

    public ConfigResponse(){}

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigContext() {
        return configContext;
    }

    public void setConfigContext(String configContext) {
        this.configContext = configContext;
    }
}
