package com.aksoftwaresolution.wellpick.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.Message.BottomSheet;
import com.aksoftwaresolution.wellpick.Monetization.AdMob;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.model.LoginPreferences;
import com.aksoftwaresolution.wellpick.model.SubCategory;
import com.bumptech.glide.Glide;
import com.google.android.ads.nativetemplates.TemplateView;

import java.util.List;

public class PremiumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NATIVE_AD = 0;
    private static final int TYPE_PREMIUM_IMAGES = 1;
    private final List<SubCategory> subList;
    private final Context context;

    //PremiumAdapter Constructor by handel this List and context
    public PremiumAdapter(List<SubCategory> subList, Context context) {
        this.subList = subList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        // Every 3 item step by step Native Ad show
        if ((position + 1) % 3 == 0) {
            return TYPE_NATIVE_AD;
        }
        //show premium Images all time
        return TYPE_PREMIUM_IMAGES;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //set Native Ad Type
        if (viewType == TYPE_NATIVE_AD) {
            View v = inflater.inflate(R.layout.native_ad_layout, parent, false);
            return new NativeAdViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.premium_subcategory_layout, parent, false);
            return new PremiumImageViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SubCategory sub = subList.get(position);

        if (holder instanceof NativeAdViewHolder) {
            NativeAdViewHolder nativeAdViewHolder = (NativeAdViewHolder) holder;
            // AdMob SDK Initialize
            AdMob.sdkInitialize(context);
            // Load Native Ad
            AdMob.setNativeAd((Activity) context, nativeAdViewHolder.my_template);

        } else if (holder instanceof PremiumImageViewHolder) {
            PremiumImageViewHolder p = (PremiumImageViewHolder) holder;
            Glide.with(context)
                    .load("https://wellpick.xyz/wellpickAdmin/img_khair/" + sub.getPremiumImages())
                    .into(p.premium_image);

            // Always show premium badge (because adapter for premium)
            p.premiumBadge.setVisibility(View.VISIBLE);
            // Item Click → BottomSheet
            p.premium_image.setOnClickListener(v -> {
                LoginPreferences loginPreferences=new LoginPreferences(context);

                if (loginPreferences.isLoggedIn()){

                }else {
                    BottomSheet.showBottomSheet(context);
                }

            });
        }
    }

    @Override
    public int getItemCount() {
        return subList.size();
    }

    // ---------------- NativeAdViewHolders --------------------

   public class NativeAdViewHolder extends RecyclerView.ViewHolder {
        TemplateView my_template;
        NativeAdViewHolder(View v) {
            super(v);
            my_template = v.findViewById(R.id.my_template);
        }
    }
    // ---------------- PremiumImageViewHolders --------------------

    public class PremiumImageViewHolder extends RecyclerView.ViewHolder {
        ImageView premium_image, premiumBadge;

        PremiumImageViewHolder(View v) {
            super(v);
            premium_image = v.findViewById(R.id.premium_image);
            premiumBadge = v.findViewById(R.id.premiumBadge);
        }
    }
}//***************************** PremiumAdapter end here **************************************************************************
