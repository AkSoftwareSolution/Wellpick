package com.aksoftwaresolution.wellpick.Monetization;

import static com.google.firebase.messaging.Constants.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdMob {

    private AdMobCallback adMobCallback;
    private static InterstitialAd minterstitialAd;

    public AdMob() {
    }

    public AdMob(AdMobCallback adMobCallback) {
        this.adMobCallback = adMobCallback;
    }

    // Initialize the Google Mobile Ads SDK on a background thread.
    public static void sdkInitialize(Context context){
        if (!Constant.IS_AD_ON)return;
        new Thread(
                () -> {
                    MobileAds.initialize(context, initializationStatus -> {});
                })
                .start();

    }// Initialize the Google Mobile Ads SDK on a background thread end here

    public static void setBannerAd(Context context, LinearLayout adContainerView){
        if (!Constant.IS_AD_ON)return;

       AdView adView = new AdView(context);
       adView.setAdUnitId(Constant.Banner_Ad);
       adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, 360));
       adContainerView.removeAllViews();
       adContainerView.addView(adView);
       AdRequest adRequest = new AdRequest.Builder().build();
       adView.loadAd(adRequest);



    }//setBannerAd end here ============



    public static void loadInterstitialAd(Context context) {
        if (!Constant.IS_AD_ON) return;

        InterstitialAd.load(
                context,
                Constant.Interstitial_Ad,
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        Log.d(TAG, "Ad was loaded.");
                        minterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d(TAG, loadAdError.getMessage());
                        minterstitialAd = null;
                    }
                });



    }//loadInterstitialAd end here ======================================

    public void showInterstitialAd(Activity activity,boolean IsReload){
        if (!Constant.IS_AD_ON){
            notifyDismiss();
            return;
        }

        if (minterstitialAd==null){
            notifyDismiss();
            return;
        }


        minterstitialAd.show(activity);
        minterstitialAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        if (IsReload){
                            minterstitialAd=null;
                            loadInterstitialAd(activity);
                        }
                        notifyDismiss();

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        notifyDismiss();
                    }


                });


    }//======showInterstitialAd end here ===================================


    private void notifyDismiss(){
        if (adMobCallback!=null){
            adMobCallback.onDismiss();
        }
    }//======notifyDismiss end here ==============================================

}
