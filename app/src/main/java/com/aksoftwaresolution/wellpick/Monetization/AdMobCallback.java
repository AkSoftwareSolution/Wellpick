package com.aksoftwaresolution.wellpick.Monetization;

import com.google.android.gms.ads.rewarded.RewardItem;

public interface AdMobCallback {
    /**
     * onDismiss start here
     */
    void  onDismiss();
    //
    default void onUserEarnedReward(RewardItem rewardItem){}
}
