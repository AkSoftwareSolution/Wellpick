package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aksoftwaresolution.wellpick.Message.BottomSheet;
import com.aksoftwaresolution.wellpick.Monetization.AdMob;
import com.aksoftwaresolution.wellpick.Monetization.AdMobCallback;
import com.aksoftwaresolution.wellpick.R;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.rewarded.RewardItem;


public class Profile_Fragment extends Fragment {

    LinearLayout adContainer;
    Button showInterstitialAd,showRewardedAd;
    TemplateView my_template;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View profileView=inflater.inflate(R.layout.fragment_profile, container, false);
        if (container!=null)container.removeAllViews();

        adContainer=profileView.findViewById(R.id.adContainer);
        showInterstitialAd=profileView.findViewById(R.id.showInterstitialAd);
        showRewardedAd=profileView.findViewById(R.id.showRewardedAd);
        my_template=profileView.findViewById(R.id.my_template);

        //AdMob.sdkInitialize
        AdMob.sdkInitialize(getContext());
        //loadInterstitialAd
        AdMob.loadInterstitialAd(getContext());
        AdMob.loadRewardedAd(getContext());
        //setBannerAd
        AdMob.setBannerAd(getContext(),adContainer);


        showInterstitialAd.setOnClickListener(v -> {

            new AdMob(() -> {

            }).showInterstitialAd(getActivity(),true);

        });

        showRewardedAd.setOnClickListener(v -> {
//            new AdMob(new AdMobCallback() {
//                @Override
//                public void onDismiss() {
//                    Toast.makeText(getContext(),"Ad is Loading.......",Toast.LENGTH_LONG).show();
//
//                }
//
//                @Override
//                public void onUserEarnedReward(RewardItem rewardItem) {
//                    int rewardAmount=rewardItem.getAmount();
//                    String rewardType=rewardItem.getType();
//
//                    Toast.makeText(getContext(),"UserEarnedReward"+rewardAmount,Toast.LENGTH_LONG).show();
//                }
//            }).showRewardedAd(getActivity(),true);

            BottomSheet.showBottomSheet(getContext());
        });

        AdMob.setNativeAd(getActivity(),my_template);







        return profileView;
    }
}