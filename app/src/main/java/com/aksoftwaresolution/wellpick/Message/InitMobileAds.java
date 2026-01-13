package com.aksoftwaresolution.wellpick.Message;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.aksoftwaresolution.wellpick.Monetization.AdMob;
import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

import java.util.concurrent.atomic.AtomicBoolean;

public class InitMobileAds {

    private static final String TAG = "GdprJuba";
    private static ConsentInformation consentInformation;
    private static AtomicBoolean isAdSdkCalled = new AtomicBoolean(false);

    /**
     * Call this from an Activity (preferably the launcher Activity) early in app flow.
     * For production remove or comment out the debugSettings lines.
     */
    public static void requestConsentForm(Activity activity) {
        // ---------- DEBUG SETTINGS (only for testing) ----------
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity)
                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_REGULATED_US_STATE)
                // Add your device hashed id only for debug/testing:
                .addTestDeviceHashedId("207FD13D4DE5BD08733B86D3B7D6D28F")
                .build();

        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                // comment the next line to turn off UMP debug mode
                //.setConsentDebugSettings(debugSettings)
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(activity);

        // Request consent info update
        consentInformation.requestConsentInfoUpdate(
                activity,
                params,
                new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {
                        Log.d(TAG, "ConsentInfo update success. consentStatus=" + consentInformation.getConsentStatus());
                        // If a form is required, load and show it
                        UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                                activity,
                                new ConsentForm.OnConsentFormDismissedListener() {
                                    @Override
                                    public void onConsentFormDismissed(com.google.android.ump.FormError loadAndShowError) {
                                        if (loadAndShowError != null) {
                                            Log.d(TAG, String.format("Consent form error: %s: %s",
                                                    loadAndShowError.getErrorCode(), loadAndShowError.getMessage()));
                                        } else {
                                            Log.d(TAG, "Consent form dismissed (no error).");
                                        }

                                        // After consent is handled (or not required) initialize ads if allowed
                                        if (consentInformation.canRequestAds()) {
                                            initMobileAdsSDK(activity.getApplicationContext());
                                        } else {
                                            Log.d(TAG, "Cannot request ads: consent not granted or restricted.");
                                        }
                                    }
                                }
                        );
                    }
                },
                new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure(com.google.android.ump.FormError formError) {
                        Log.w(TAG, String.format("ConsentInfo update failed: %s: %s",
                                formError.getErrorCode(), formError.getMessage()));
                        // Even on failure you may choose to init the SDK with limited ads or none.
                        // Here we try to init but only if you want; for privacy-safe approach, avoid init.
                        // initMobileAdsSDK(activity.getApplicationContext());
                    }
                }
        );
    }

    // ---------------------------------------------------------
    private static void initMobileAdsSDK(Context context) {
        // Ensure SDK initialized once
        if (isAdSdkCalled.getAndSet(true)) {
            Log.d(TAG, "Mobile Ads SDK already initialized.");
            return;
        }

        Log.d(TAG, "Initializing Mobile Ads SDK...");
        // If you have a wrapper AdMob.sdkInitialize(context) use it; otherwise use MobileAds directly:
        try {
            AdMob.sdkInitialize(context); // <-- keep using your existing wrapper
        } catch (Exception e) {
            // fallback to direct init if wrapper fails / not present
            MobileAds.initialize(context, initializationStatus -> {
                Log.d(TAG, "MobileAds.initialize callback.");
            });
        }

        Log.d(TAG, "Mobile Ads SDK init called.");
    }



    // Optional helper: only for manual testing (uncomment and call once if you want to reset consent state)
    public static void debugResetConsent() {
        if (consentInformation != null) {
            consentInformation.reset();
            Log.d(TAG, "consentInformation.reset() called (debug).");
        } else {
            Log.d(TAG, "consentInformation is null; cannot reset.");
        }
    }
}
