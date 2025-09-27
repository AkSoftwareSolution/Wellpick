package com.aksoftwaresolution.wellpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.CryptoUtil.CryptoUtil;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.User;

import java.util.Collections;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {


    private List<CategoryList> categoryList;
    Context context;
    CryptoUtil cryptoUtil=new CryptoUtil();

    public CategoryAdapter(List<CategoryList> CategoryListList, Context context) {
        this.categoryList = CategoryListList;
        this.context = context;
        Collections.shuffle(CategoryListList);

    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_layout, parent, false);
        return new CategoryAdapter.CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {
        CategoryList user=categoryList.get(position);

        try {
            holder.CategoryTitle.setText(cryptoUtil.decrypt(user.getCategory_name()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class CategoryHolder extends RecyclerView.ViewHolder {
        TextView CategoryTitle;
        CardView item_click;

        CategoryHolder(@NonNull View itemView) {
            super(itemView);
            CategoryTitle = itemView.findViewById(R.id.CategoryTitle);
            item_click = itemView.findViewById(R.id.item_click);


        }
    }
}