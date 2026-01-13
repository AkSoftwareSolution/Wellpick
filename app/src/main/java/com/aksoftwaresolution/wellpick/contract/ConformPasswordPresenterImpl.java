package com.aksoftwaresolution.wellpick.contract;

public class ConformPasswordPresenterImpl implements ConformPasswordContract.Presenter {

    private ConformPasswordContract.View view;

    public ConformPasswordPresenterImpl(ConformPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void validatePassword(String newPassword, String confirmPassword) {

        if (view == null) return;

        view.clearErrors();

        if (newPassword == null || newPassword.isEmpty()) {
            view.showNewPasswordError("Enter new password");
            return;
        }

        if (newPassword.length() < 6) {
            view.showNewPasswordError("Minimum 6 characters required");
            return;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            view.showConfirmPasswordError("Confirm your password");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            view.showConfirmPasswordError("Password does not match");
            return;
        }

        view.onPasswordSuccess();
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
