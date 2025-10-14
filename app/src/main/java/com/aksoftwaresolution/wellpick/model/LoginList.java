package com.aksoftwaresolution.wellpick.model;

public class LoginList {

    String LoginId;
    String LoginName;
    String LoginEmail;

    public LoginList(String LoginId, String LoginName, String LoginEmail) {
        this.LoginId = LoginId;
        this.LoginName = LoginName;
        this.LoginEmail = LoginEmail;

    }

    public String getLoginId() {
        return LoginId;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getLoginEmail() {
        return LoginEmail;
    }
}
