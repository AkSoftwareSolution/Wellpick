package com.aksoftwaresolution.wellpick.Monetization;

import android.app.Application;

public class AdMobSdkInitialize extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AdMob.sdkInitialize(this);

    }
}

