package com.not.byko.jarlvpn_android_adminpanel.models;

public class DownloadPrivateKeyResponse {

    public String fileNamePpk;
    public String fileContentPpk;

    public DownloadPrivateKeyResponse(String fileNamePpk, String fileContentPpk) {
        this.fileNamePpk = fileNamePpk;
        this.fileContentPpk = fileContentPpk;
    }

    public DownloadPrivateKeyResponse(){}

    public String getFileNamePpk() {
        return fileNamePpk;
    }

    public void setFileNamePpk(String fileNamePpk) {
        this.fileNamePpk = fileNamePpk;
    }

    public String getFileContentPpk() {
        return fileContentPpk;
    }

    public void setFileContentPpk(String fileContentPpk) {
        this.fileContentPpk = fileContentPpk;
    }
}
