package com.aksoftwaresolution.wellpick.Monetization;

import com.google.android.gms.ads.rewarded.RewardItem;

public interface AdMobCallback {

    void  onDismiss();
    default void onUserEarnedReward(RewardItem rewardItem){}
}
