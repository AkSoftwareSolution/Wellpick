package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aksoftwaresolution.wellpick.Monetization.AdMob;
import com.aksoftwaresolution.wellpick.R;


public class Profile_Fragment extends Fragment {

    LinearLayout adContainer;
    Button showInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View profileView=inflater.inflate(R.layout.fragment_profile, container, false);
        if (container!=null)container.removeAllViews();

        adContainer=profileView.findViewById(R.id.adContainer);
        showInterstitialAd=profileView.findViewById(R.id.showInterstitialAd);

        //AdMob.sdkInitialize
        AdMob.sdkInitialize(getContext());
        //loadInterstitialAd
        AdMob.loadInterstitialAd(getContext());
        //setBannerAd
        AdMob.setBannerAd(getContext(),adContainer);


        showInterstitialAd.setOnClickListener(v -> {

            new AdMob(() -> {

            }).showInterstitialAd(getActivity(),true);

        });







        return profileView;
    }
}