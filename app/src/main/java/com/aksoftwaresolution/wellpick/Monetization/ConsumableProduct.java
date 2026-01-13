package com.aksoftwaresolution.wellpick.Monetization;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.PendingPurchasesParams;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryProductDetailsResult;
import com.android.billingclient.api.QueryPurchasesParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConsumableProduct {
    private Context context;
    private BillingClient billingClient;
    private String ProductId;

    private MyBillingListener myBillingListener;

    public interface MyBillingListener{
        void onPurchasesSuccess(Purchase purchase);
        void onPurchasesFailed();
        void onPurchasesCanceled();
        void initBillingLibraryReady();

    }

    public ConsumableProduct(Context context,MyBillingListener myBillingListener){
        this.myBillingListener=myBillingListener;
        this.context=context;
        initBillingLibrary();
    }

 private void initBillingLibrary(){
        billingClient= BillingClient.newBuilder(context)
                .enablePendingPurchases(
                        PendingPurchasesParams.newBuilder()
                                .enableOneTimeProducts()
                                .build())
                .setListener(new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
                        if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK&&!list.isEmpty()){
                            for (Purchase purchase:list){
                                if (purchase.getProducts().contains(ProductId)){
                                    if (purchase.getPurchaseState()==Purchase.PurchaseState.PURCHASED){
                                        Log.d("khairBilling","Purchase Success");
                                        myBillingListener.onPurchasesSuccess(purchase);

                                    }else {
                                        Log.d("khairBilling","Purchase is failed");
                                        myBillingListener.onPurchasesFailed();
                                    }
                                   break;
                                }
                            }

                        }else if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.USER_CANCELED){
                            Log.d("khairBilling","Purchase is user canceled ");
                            myBillingListener.onPurchasesCanceled();
                        }else {
                            Log.d("khairBilling","Purchase is failed (2) ");
                            myBillingListener.onPurchasesFailed();
                        }

                    }
                })
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                Log.d("khairBilling","BillingServiceDisconnected");

            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                Log.d("khairBilling","BillingSetupFinished");
                myBillingListener.initBillingLibraryReady();

            }
        });

 }//-------------------------------------------initBillingLibrary end here ---------------------------

    public void requestPurchases(String productId){
        this.ProductId=productId;
        //Get ProductDetails
        QueryProductDetailsParams detailsParams=QueryProductDetailsParams.newBuilder()
                .setProductList(Collections.singletonList(
                        QueryProductDetailsParams.Product.newBuilder()
                                .setProductId(ProductId)
                                .setProductType(BillingClient.ProductType.INAPP)
                                .build()
                ))
                .build();
        billingClient.queryProductDetailsAsync(detailsParams, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull QueryProductDetailsResult queryProductDetailsResult) {
                Log.d("khairBilling","queryProductDetailsAsync");

                if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK&&!queryProductDetailsResult.getProductDetailsList().isEmpty()){
                    for (ProductDetails productDetails:queryProductDetailsResult.getProductDetailsList()){

                        if (productDetails.getProductId().contains(ProductId)){
                            Log.d("khairBilling","product found. product id : "+ProductId);
                            launchBillingFlow(productDetails);
                            break;

                        }

                    }// end of for loop

                }// end of if

            }
        });



    }
//-----------------------------requestPurchases end here ---------------------------------
    private void launchBillingFlow(ProductDetails productDetails){

        List<BillingFlowParams.ProductDetailsParams> detailsParamsList=Collections.singletonList(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build()
        );

        BillingFlowParams billingFlowParams= BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(detailsParamsList)
                .build();

        billingClient.launchBillingFlow((Activity) context,billingFlowParams);

    }//---------------------launchBillingFlow end here -------------------------------------

    public void consumePurchases(Purchase purchase){

        ConsumeParams consumeParams=ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();

        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
                Log.d("khairBilling","ConsumeResponseSuccess");

            }
        });


    }//---------------------------------------consumePurchases end here --------------------------------------

    public void acknowledgePurchases(Purchase purchase){
        AcknowledgePurchaseParams acknowledgePurchaseParams=AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();
        billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
            @Override
            public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {

            }
        });

    }//------------------acknowledgePurchases end here ------------------------------------------------------------

    public interface queryAllProductsListener{
        void onQueryAllProductsSuccess(ArrayList<HashMap<String,String>> arrayList);
    }



    public void queryAllProducts(String[]productIdArray,queryAllProductsListener allProductsListener){

        List<QueryProductDetailsParams.Product>productList=new ArrayList<>();
        for (String myProductId :productIdArray){
            QueryProductDetailsParams.Product product= QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(myProductId)
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build();
            productList.add(product);

        }



        //Get ProductDetails
        QueryProductDetailsParams detailsParams=QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build();
        billingClient.queryProductDetailsAsync(detailsParams, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull QueryProductDetailsResult queryProductDetailsResult) {
                Log.d("khairBilling","queryProductDetailsAsync");

                if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK&&!queryProductDetailsResult.getProductDetailsList().isEmpty()){

                    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();

                    for (ProductDetails productDetails:queryProductDetailsResult.getProductDetailsList()){

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("productId",productDetails.getProductId());
                        hashMap.put("name",productDetails.getName());
                        hashMap.put("price",productDetails.getOneTimePurchaseOfferDetails().getFormattedPrice());
                        arrayList.add(hashMap);



                    }// end of for loop
                    allProductsListener.onQueryAllProductsSuccess(arrayList);

                }// end of if

            }
        });



    }//-----------------------------------------------------------------------

//----------------------------------------------------------------------------
public interface chackPurchasesBeforeListener{
        void onChackPurchasesBeforeSuccess(boolean PurchasedBefore);
}

    public void chackPurchasesBefore(String productId,chackPurchasesBeforeListener chackPurchasesBeforeListener){
        QueryPurchasesParams queryPurchasesParams=QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.INAPP)
                .build();

        billingClient.queryPurchasesAsync(queryPurchasesParams, new PurchasesResponseListener() {
            @Override
            public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> Purchaselist) {
                boolean alreadyPurchased=false;

                if (billingResult.getResponseCode()==BillingClient.BillingResponseCode.OK&&!Purchaselist.isEmpty() ){
                    for (Purchase purchase:Purchaselist){
                        if (purchase.getProducts().contains(productId)&&purchase.getPurchaseState()==Purchase.PurchaseState.PURCHASED){
                            Log.d("khairBilling","Purchase Before");
                            alreadyPurchased=true;
                            break;

                        }//--if end here else

                    }//---for loop end here

                }//----if end here

                if (alreadyPurchased){
                    Log.d("khairBilling","Purchase Before");

                }else {
                    Log.d("khairBilling","Purchase Not Before");
                }
                chackPurchasesBeforeListener.onChackPurchasesBeforeSuccess(alreadyPurchased);


            }
        });

    }//--------------------------------------------------------------------------------




}//------------------------------------------------------------------------------
