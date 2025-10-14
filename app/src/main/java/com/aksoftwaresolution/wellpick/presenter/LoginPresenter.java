package com.aksoftwaresolution.wellpick.presenter;

import com.aksoftwaresolution.wellpick.contract.LoginContract;
import com.aksoftwaresolution.wellpick.model.LoginList;

public class LoginPresenter implements LoginContract.LoginPresenter,LoginContract.LoginModel.OnLoginFinishedListener,LoginContract.LoginModel.OnSignUpListener {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    public LoginPresenter(LoginContract.LoginView loginView,LoginContract.LoginModel loginModel){
        this.loginView=loginView;
        this.loginModel=loginModel;
    }



    @Override
    public void onSuccess(LoginList loginList) {
        if (loginView!=null){
            loginView.hideLoading();
            loginView.onLoginSuccess(loginList);
        }

    }

    @Override
    public void onFailure(String message) {
        if (loginView!=null){
            loginView.hideLoading();
            loginView.onLoginFailure(message);
        }

    }

    @Override
    public void login(String loginName, String loginPassword) {

        if (loginView!=null){
            loginView.showLoading();
            loginModel.doLogin(loginName,loginPassword,this);
        }

    }

    @Override
    public void signUp(String name, String email, String password) {
        if (loginView!=null){
            loginView.showLoading();
            loginModel.signUpUser(name,email,password,this);
        }

    }

    @Override
    public void onSignUpSuccess(String message) {
        if (loginView!=null){
            loginView.hideLoading();
            loginView.onSignUpSuccess(message);
        }

    }

    @Override
    public void onSignUpFailure(String error) {
        if (loginView!=null){
            loginView.hideLoading();
            loginView.onSignUpFailure(error);
        }

    }
}
