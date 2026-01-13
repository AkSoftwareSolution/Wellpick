package com.aksoftwaresolution.wellpick.contract;

public interface ConformPasswordContract {

    interface View {
        void showNewPasswordError(String message);
        void showConfirmPasswordError(String message);
        void clearErrors();
        void onPasswordSuccess();
    }

    interface Presenter {
        void validatePassword(String newPassword, String confirmPassword);
        void onDestroy();
    }


  interface Model{
       void chackingPassword(chackingPasswordFinishedListener passwordFinishedListener);

       interface chackingPasswordFinishedListener{
           void onPasswordSuccess(String message);
           void onPasswordFailure(String message);
       }




  }



}
