package com.aksoftwaresolution.wellpick.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.aksoftwaresolution.wellpick.Monetization.SubscriptionProduct;
import com.aksoftwaresolution.wellpick.R;
import com.android.billingclient.api.Purchase;

public class SubscriptionActivity extends AppCompatActivity {

    private CardView cardMonthly, cardSixMonth, cardYearly;
    private Button btnPurchase;
    private String selectedPlan = ""; // Tracks the selected plan
    private SubscriptionProduct subscriptionProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        // Initialize views
        cardMonthly = findViewById(R.id.cardMonthly);
        cardSixMonth = findViewById(R.id.cardSixMonth);
        cardYearly = findViewById(R.id.cardYearly);
        btnPurchase = findViewById(R.id.btnPurchase);

        // Initialize SubscriptionProduct helper
        subscriptionProduct = new SubscriptionProduct(this, new SubscriptionProduct.MyBillingListener() {
            @Override
            public void onPurchasesSuccess(Purchase purchase) {
                Toast.makeText(SubscriptionActivity.this, "Subscription successful!", Toast.LENGTH_SHORT).show();
                subscriptionProduct.acknowledgePurchases(purchase);
            }

            @Override
            public void onPurchasesFailed() {
                Toast.makeText(SubscriptionActivity.this, "Purchase failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPurchasesCanceled() {
                Toast.makeText(SubscriptionActivity.this, "Purchase canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void initBillingLibraryReady() {
                // Optional: check existing subscriptions
            }
        });

        // Plan selection listeners
        cardMonthly.setOnClickListener(v -> selectPlan("month"));
        cardSixMonth.setOnClickListener(v -> selectPlan("six_month"));
        cardYearly.setOnClickListener(v -> selectPlan("year"));

        // Purchase button listener
        btnPurchase.setOnClickListener(v -> handlePurchase());
    }

    /**
     * Handles selecting a plan and updating UI highlight
     */
    private void selectPlan(String plan) {
        selectedPlan = plan;

        // Reset all cards to default colors
        cardMonthly.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        cardSixMonth.setCardBackgroundColor(getResources().getColor(R.color.card_six_month)); // #FFF7E6
        cardYearly.setCardBackgroundColor(getResources().getColor(R.color.card_yearly));      // #E8EBFF

        // Highlight selected card
        switch (plan) {
            case "month":
                cardMonthly.setCardBackgroundColor(getResources().getColor(R.color.card_selected)); // custom highlight color
                break;
            case "six_month":
                cardSixMonth.setCardBackgroundColor(getResources().getColor(R.color.card_selected));
                break;
            case "year":
                cardYearly.setCardBackgroundColor(getResources().getColor(R.color.card_selected));
                break;
        }
    }

    /**
     * Initiates purchase based on selected plan
     */
    private void handlePurchase() {
        if (selectedPlan.isEmpty()) {
            Toast.makeText(this, "Please select a plan", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (selectedPlan) {
            case "month":
                subscriptionProduct.chackPurchasesBefore("monthly_premium", purchasedBefore -> runOnUiThread(() -> {
                    if (purchasedBefore) {
                        Toast.makeText(SubscriptionActivity.this, "You already have a subscription", Toast.LENGTH_SHORT).show();
                    } else {
                        subscriptionProduct.requestPurchases("monthly_premium", "7-days-free-trial");
                    }
                }));
                break;

            case "six_month":
                subscriptionProduct.chackPurchasesBefore("six_month_premium", purchasedBefore -> runOnUiThread(() -> {
                    if (purchasedBefore) {
                        Toast.makeText(SubscriptionActivity.this, "You already have a subscription", Toast.LENGTH_SHORT).show();
                    } else {
                        subscriptionProduct.requestPurchases("six_month_premium", "7-days-free-trial");
                    }
                }));
                break;

            case "year":
                subscriptionProduct.chackPurchasesBefore("yearly_premium", purchasedBefore -> runOnUiThread(() -> {
                    if (purchasedBefore) {
                        Toast.makeText(SubscriptionActivity.this, "You already have a subscription", Toast.LENGTH_SHORT).show();
                    } else {
                        subscriptionProduct.requestPurchases("yearly_premium", "7-days-free-trial");
                    }
                }));
                break;
        }
    }
}
