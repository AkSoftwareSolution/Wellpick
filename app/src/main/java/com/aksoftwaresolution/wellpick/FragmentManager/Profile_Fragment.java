package com.aksoftwaresolution.wellpick.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aksoftwaresolution.wellpick.Message.CustomDialog;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.activity.ChangePasswordActivity;
import com.aksoftwaresolution.wellpick.activity.PrivacyPolicyActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;


public class Profile_Fragment extends Fragment {
    TextView name,email,profileStats;
    ImageView DeleteAccount,PrivacyPolicy,logout,edit,ChangePassword;
    SwitchMaterial onNotification;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View profileView=inflater.inflate(R.layout.fragment_profile, container, false);
        if (container!=null)container.removeAllViews();

        name=profileView.findViewById(R.id.name);
        email=profileView.findViewById(R.id.email);
        edit=profileView.findViewById(R.id.edit);
        DeleteAccount=profileView.findViewById(R.id.DeleteAccount);
        PrivacyPolicy=profileView.findViewById(R.id.PrivacyPolicy);
        logout=profileView.findViewById(R.id.logout);
        profileStats=profileView.findViewById(R.id.profileStats);
        onNotification=profileView.findViewById(R.id.onNotification);
        ChangePassword=profileView.findViewById(R.id.ChangePassword);




        PrivacyPolicy.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));

        });
        edit.setOnClickListener(v -> {
            CustomDialog.showEditDialog(getContext());
        });

        logout.setOnClickListener(v -> {
            CustomDialog.showLogoutDialog(getContext());
        });

        DeleteAccount.setOnClickListener(v -> {
            CustomDialog.showDeleteAccountDialog(getContext());
        });

        ChangePassword.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getContext(), ChangePasswordActivity.class));
        });




        return profileView;
    }
}