package com.aksoftwaresolution.wellpick.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.aksoftwaresolution.wellpick.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheet {

    // make them global for selectTab() access
    private static TextView tvLogin, tvSignup;

    public static void showBottomSheet(Context context) {

        BottomSheetDialog sheetDialog = new BottomSheetDialog(context);
        View sheetView = LayoutInflater.from(context).inflate(R.layout.sheet_layout, null);
        sheetDialog.setContentView(sheetView);

        ImageView closeIcon = sheetView.findViewById(R.id.closeIcon);
        LinearLayout loginLayout=sheetView.findViewById(R.id.loginLayout);
        LinearLayout layoutSingUp=sheetView.findViewById(R.id.layoutSingUp);
        tvLogin = sheetView.findViewById(R.id.ClickLogin);
        tvSignup = sheetView.findViewById(R.id.ClickSingUp);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.tab_select_anim);

        closeIcon.setOnClickListener(v -> sheetDialog.dismiss());

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

        sheetDialog.show();
    }

    // make it static and pass context
    private static void selectTab(Context context, boolean isLogin, Animation anim) {
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
}
