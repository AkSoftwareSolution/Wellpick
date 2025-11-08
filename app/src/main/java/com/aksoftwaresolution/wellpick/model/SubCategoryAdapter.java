package com.aksoftwaresolution.wellpick.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubViewHolder> {
    private List<SubCategory> subList;
    private Context context;

    public SubCategoryAdapter(List<SubCategory> subList, Context context) {
        this.subList = subList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subcategory_layout, parent, false);
        return new SubViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        SubCategory sub = subList.get(position);


        Glide.with(context).
                load("https://wellpick.xyz/wellpickAdmin/img_khair/"+sub.getImage_url()).into(holder.subcategory_image);
    }

    @Override
    public int getItemCount() {
        return subList.size();
    }

    static class SubViewHolder extends RecyclerView.ViewHolder {

        ImageView subcategory_image;
        SubViewHolder(View v) {
            super(v);

            subcategory_image = v.findViewById(R.id.subcategory_image);
        }
    }
}

