package com.aksoftwaresolution.wellpick.model;

import android.content.Context;

import com.aksoftwaresolution.wellpick.contract.LoginContract;

public class LoginModel implements LoginContract.LoginModel  {
    private Context context;

    public LoginModel(Context context){
        this.context=context;

    }

    @Override
    public void doLogin(String loginName, String loginPassword, OnLoginFinishedListener loginFinishedListener) {

    }

    @Override
    public void signUpUser(String name, String email, String password, OnSignUpListener listener) {

    }
}
