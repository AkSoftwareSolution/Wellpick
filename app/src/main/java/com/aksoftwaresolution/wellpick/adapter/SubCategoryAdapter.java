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

public class SubCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SUBIMAGES = 0;
    private static final int TYPE_NATIVE_AD = 1;
    private static final int TYPE_PREMIUM_IMAGES = 2;
    private final List<SubCategory> subList;
    private final Context context;



    //SubCategoryAdapter Constructor by handel this List and context
    public SubCategoryAdapter(List<SubCategory> subList, Context context) {
        this.subList = subList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        //position by show ad TYPE_NATIVE_AD
        if ((position + 1) % 3 == 0) {
            return TYPE_NATIVE_AD;
        }
        //Initialize SubCategory Item List
        SubCategory item = subList.get(position);
        //Return to TYPE_PREMIUM_IMAGES View Type
        if (item.isPremium()) {
            return TYPE_PREMIUM_IMAGES;
        }
        //Return to SubCategoryImages View type
        return TYPE_SUBIMAGES;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        //TYPE_SUBIMAGES
        if (viewType == TYPE_SUBIMAGES) {
            View v = inflater.inflate(R.layout.subcategory_layout, parent, false);
            return new SubImageViewHolder(v);
            //TYPE_NATIVE_AD
        } else if (viewType == TYPE_NATIVE_AD) {
            View v = inflater.inflate(R.layout.native_ad_layout, parent, false);
            return new NativeAdViewHolder(v);
        } else { // TYPE_PREMIUM_IMAGES
            View v = inflater.inflate(R.layout.premium_subcategory_layout, parent, false);
            return new PremiumImageViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SubCategory sub = subList.get(position);

        if (holder instanceof SubImageViewHolder) {
            SubImageViewHolder h = (SubImageViewHolder) holder;
            //Load SubImage by Internet use Glide library get subImages list
            Glide.with(context)
                    .load("https://wellpick.xyz/wellpickAdmin/img_khair/" + sub.getImage_url())
                    .into(h.subcategory_image);

            h.subcategory_image.setOnClickListener(v -> {
                LoginPreferences prefs = new LoginPreferences(v.getContext());
                if (prefs.isLoggedIn()) {
                    //Login Acetoin click handel
                } else {
                    BottomSheet.showBottomSheet(v.getContext());
                }
            });
            //NativeAdViewHolder handel

        } else if (holder instanceof NativeAdViewHolder) {
            NativeAdViewHolder nativeAdViewHolder= (NativeAdViewHolder) holder;


            //set admob show setNativeAd
            AdMob.setNativeAd((Activity) context,  nativeAdViewHolder.my_template);

        } else if (holder instanceof PremiumImageViewHolder) {
            PremiumImageViewHolder p = (PremiumImageViewHolder) holder;
            //Load PremiumImages by Internet use Glide library get subImages list
            Glide.with(context)
                    .load("https://wellpick.xyz/wellpickAdmin/img_khair/" + sub.getImage_url())
                    .into(p.premium_image);

            p.premiumBadge.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return subList.size();
    }


    static class SubImageViewHolder extends RecyclerView.ViewHolder {
        ImageView subcategory_image;

        SubImageViewHolder(View v) {
            super(v);
            subcategory_image = v.findViewById(R.id.subcategory_image);
        }
    }

    //  Native Ad
    static class NativeAdViewHolder extends RecyclerView.ViewHolder {
        TemplateView my_template;

        NativeAdViewHolder(View v) {
            super(v);
            my_template = v.findViewById(R.id.my_template);
        }
    }

    //  Premium Image
    static class PremiumImageViewHolder extends RecyclerView.ViewHolder {
        ImageView premium_image, premiumBadge;

        PremiumImageViewHolder(View v) {
            super(v);
            premium_image = v.findViewById(R.id.premium_image);
            premiumBadge = v.findViewById(R.id.premiumBadge);
        }
    }
}
