package com.aksoftwaresolution.wellpick.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aksoftwaresolution.wellpick.Monetization.SubscriptionProduct;
import com.aksoftwaresolution.wellpick.R;
import com.android.billingclient.api.Purchase;

public class SubscriptionActivity extends AppCompatActivity {

    SubscriptionProduct subscriptionProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subscription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout monthCard =findViewById(R.id.monthCard);
        LinearLayout yearCard  = findViewById(R.id.yearCard);
        Button btnPay = findViewById(R.id.btnPay);


        final String[] selectedPlan = {"year"};
        //monthly_premium
        //7-days-free-trial



        monthCard.setOnClickListener(v -> {
            selectedPlan[0] = "month";
            monthCard.setBackgroundResource(R.drawable.card_select);
            yearCard.setBackgroundResource(R.drawable.btn_neon);
        });

        yearCard.setOnClickListener(v -> {
            selectedPlan[0] = "year";
            yearCard.setBackgroundResource(R.drawable.card_select);
            monthCard.setBackgroundResource(R.drawable.btn_neon);
        });

        subscriptionProduct=new SubscriptionProduct(this, new SubscriptionProduct.MyBillingListener() {
            @Override
            public void onPurchasesSuccess(Purchase purchase) {


                Toast.makeText(SubscriptionActivity.this, "Subscription is Successfully", Toast.LENGTH_SHORT).show();

                subscriptionProduct.acknowledgePurchases(purchase);


            }

            @Override
            public void onPurchasesFailed() {

            }

            @Override
            public void onPurchasesCanceled() {

            }

            @Override
            public void initBillingLibraryReady() {
                subscriptionProduct.chackPurchasesBefore("monthly_premium", new SubscriptionProduct.chackPurchasesBeforeListener() {
                    @Override
                    public void onChackPurchasesBeforeSuccess(boolean PurchasedBefore) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (PurchasedBefore){
                                    Toast.makeText(SubscriptionActivity.this, "You already have a subscription", Toast.LENGTH_SHORT).show();

                                }else {
                                    subscriptionProduct.requestPurchases("monthly_premium","7-days-free-trial");
                                }


                            }
                        });

                    }
                });

            }
        });


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPlan[0].equals("month")){
                    subscriptionProduct.chackPurchasesBefore("monthly_premium", new SubscriptionProduct.chackPurchasesBeforeListener() {
                        @Override
                        public void onChackPurchasesBeforeSuccess(boolean PurchasedBefore) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (PurchasedBefore){
                                        Toast.makeText(SubscriptionActivity.this, "You already have a subscription", Toast.LENGTH_SHORT).show();

                                    }else {
                                        subscriptionProduct.requestPurchases("monthly_premium","7-days-free-trial");
                                    }


                                }
                            });

                        }
                    });
                }


            }
        });



    }
}