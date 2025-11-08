package com.aksoftwaresolution.wellpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.model.MultipleItemList;

import java.util.List;
import java.util.Random;

public class MultipleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NATIVE_AD = 0;
    private static final int TYPE_PREMIUM_IMAGES = 1;
    private static final int TYPE_POPULAR_IMAGES = 2;

    private List<MultipleItemList> itemList;
    private Random random;
    private Context context;

    public MultipleAdapter(List<MultipleItemList> itemList, Context context) {
        this.itemList = itemList;
        this.random = new Random();
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        // Randomly insert native ads every few items
        if (position != 0 && position % 5 == 0) { // প্রতি 5টা আইটেম পর পর একবার ad
            return TYPE_NATIVE_AD;
        } else {
            // Randomly choose between premium and popular
            return random.nextBoolean() ? TYPE_PREMIUM_IMAGES : TYPE_POPULAR_IMAGES;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NATIVE_AD) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.native_ad_layout, parent, false);
            return new NativeAdViewHolder(view);
        } else if (viewType == TYPE_PREMIUM_IMAGES) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_premium, parent, false);
            return new PremiumViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_popular, parent, false);
            return new PopularViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        MultipleItemList multipleItemList=itemList.get(position);

        if (viewType == TYPE_NATIVE_AD) {
            NativeAdViewHolder adHolder = (NativeAdViewHolder) holder;

        } else if (viewType == TYPE_PREMIUM_IMAGES) {
            PremiumViewHolder premiumHolder = (PremiumViewHolder) holder;

        } else {
            PopularViewHolder popularHolder = (PopularViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // =============== ViewHolders ==================

    static class NativeAdViewHolder extends RecyclerView.ViewHolder {
        public NativeAdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class PremiumViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        public PremiumViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ItemImages);
            title = itemView.findViewById(R.id.CategoryTitle);
        }
    }

    static class PopularViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ItemImages);
        }
    }
}
