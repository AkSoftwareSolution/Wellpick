package com.aksoftwaresolution.wellpick.Message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.activity.PrivacyPolicyActivity;
import com.aksoftwaresolution.wellpick.contract.LoginContract;
import com.aksoftwaresolution.wellpick.model.LoginModel;
import com.aksoftwaresolution.wellpick.model.LoginPreferences;
import com.aksoftwaresolution.wellpick.presenter.LoginPresenter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

public class BottomSheet extends BottomSheetDialog implements LoginContract.LoginView {

    private final TextView tvLogin;
    private final TextView tvSignup;
    private final TextView tvGoPrivacyPolicy;
    private final TextView tvPrivacyPolicy;
    private final LinearLayout loginLayout;
    private final LinearLayout layoutSingUp;
    private final AppCompatButton btLogin;
    private final TextInputEditText edLoginEmail;
    private final TextInputEditText edLoginPassword;
    private final LoginPresenter loginPresenter;

    public BottomSheet(Context context) {
        super(context);

        View sheetView = LayoutInflater.from(context).inflate(R.layout.sheet_layout, null);
        setContentView(sheetView);

        // presenter initialization
        loginPresenter = new LoginPresenter(this, new LoginModel(context));

        ImageView closeIcon = sheetView.findViewById(R.id.closeIcon);
        loginLayout = sheetView.findViewById(R.id.loginLayout);
        layoutSingUp = sheetView.findViewById(R.id.layoutSingUp);
        tvLogin = sheetView.findViewById(R.id.ClickLogin);
        btLogin = sheetView.findViewById(R.id.btLogin);
        tvSignup = sheetView.findViewById(R.id.ClickSingUp);
        edLoginPassword = sheetView.findViewById(R.id.edLoginPassword);
        edLoginEmail = sheetView.findViewById(R.id.edLoginEmail);
        tvGoPrivacyPolicy = sheetView.findViewById(R.id.tvGoPrivacyPolicy);
        tvPrivacyPolicy = sheetView.findViewById(R.id.tvPrivacyPolicy);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tab_select_anim);

        closeIcon.setOnClickListener(v -> dismiss());

        // login button click
        tvLogin.setOnClickListener(v -> {
            selectTab(context, true, anim);
            loginLayout.setVisibility(View.VISIBLE);
            layoutSingUp.setVisibility(View.GONE);
        });

        // signup button click
        tvSignup.setOnClickListener(v -> {
            selectTab(context, false, anim);
            loginLayout.setVisibility(View.GONE);
            layoutSingUp.setVisibility(View.VISIBLE);
        });

        btLogin.setOnClickListener(v -> {
            String email = edLoginEmail.getText().toString().trim();
            String password = edLoginPassword.getText().toString().trim();
            loginPresenter.login(email, password);
        });

        tvGoPrivacyPolicy.setOnClickListener(v -> {
            dismiss(); // BottomSheet বন্ধ
            context.startActivity(new Intent(context, PrivacyPolicyActivity.class));
        });
        tvPrivacyPolicy.setOnClickListener(v -> {
            dismiss();
            context.startActivity(new Intent(context, PrivacyPolicyActivity.class));
        });

    }

    public static void showBottomSheet(Context context) {
        BottomSheet bottomSheet = new BottomSheet(context);
        bottomSheet.show();
    }

    private void selectTab(Context context, boolean isLogin, Animation anim) {
        if (isLogin) {
            tvLogin.setBackgroundResource(R.drawable.tab_selected);
            tvSignup.setBackgroundResource(R.drawable.tab_unselected);
            tvLogin.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            tvSignup.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));
            tvLogin.startAnimation(anim);
        } else {
            tvSignup.setBackgroundResource(R.drawable.tab_selected);
            tvLogin.setBackgroundResource(R.drawable.tab_unselected);
            tvSignup.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            tvLogin.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));
            tvSignup.startAnimation(anim);
        }
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        // hide progress
    }

    @Override
    public void onLoginSuccess(String message) {
       //LoginSaved Data successful
        new LoginPreferences(getContext()).saveLoginSession(edLoginEmail.getText().toString().trim());
      //BottomSheetDialog dismiss
        dismiss();
    }

    @Override
    public void onLoginFailure(String message) {
        Toast.makeText(getContext(), "Login Failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess(String message) {
        Toast.makeText(getContext(), "Signup Success: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpFailure(String error) {
        Toast.makeText(getContext(), "Signup Failed: " + error, Toast.LENGTH_SHORT).show();
    }
}
