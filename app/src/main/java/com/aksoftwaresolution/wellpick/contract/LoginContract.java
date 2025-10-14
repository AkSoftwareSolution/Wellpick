package com.aksoftwaresolution.wellpick.contract;

import com.aksoftwaresolution.wellpick.model.LoginList;

public class LoginContract {

   public interface LoginView {
        void showLoading();

        void hideLoading();

        void onLoginSuccess(LoginList loginList);

        void onLoginFailure(String message);

       void onSignUpSuccess(String message);
       void onSignUpFailure(String error);
    }

   public interface LoginPresenter {
        void login(String loginName, String loginPassword);
       void signUp(String name, String email, String password);


    }


    public interface LoginModel {
        void doLogin(String loginName, String loginPassword, OnLoginFinishedListener loginFinishedListener);

        void signUpUser(String name, String email, String password, OnSignUpListener listener);

        interface OnSignUpListener {
            void onSignUpSuccess(String message);
            void onSignUpFailure(String error);
        }

        interface OnLoginFinishedListener {
            void onSuccess(LoginList loginList);

            void onFailure(String message);

        }


    }


}
