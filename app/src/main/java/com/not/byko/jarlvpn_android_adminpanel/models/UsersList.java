package com.not.byko.jarlvpn_android_adminpanel.models;

import java.util.List;

public class UsersList {

    private List<String> users;

    public UsersList(List<String> users){
        this.users = users;
    }

    public UsersList(){}


    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
