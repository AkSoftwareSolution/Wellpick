package com.aksoftwaresolution.wellpick.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aksoftwaresolution.wellpick.CryptoUtil.CryptoUtil;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private List<CategoryList> categoryList;
    private int selectedPosition = 0; //Default select first item
    private final CryptoUtil cryptoUtil = new CryptoUtil();
    private final OnCategoryClickListener listener;
    private final RecyclerView SubCategoryRecyclerView;

    public interface OnCategoryClickListener {
        void onCategoryClick(String categoryId);
    }

    public CategoryAdapter(List<CategoryList> categoryList,
                           RecyclerView SubCategoryRecyclerView,
                           OnCategoryClickListener listener) {
        this.categoryList = categoryList;
        this.listener = listener;
        this.SubCategoryRecyclerView = SubCategoryRecyclerView;

        //categoryList not empty data auto-select categoryItem
        if (categoryList != null && !categoryList.isEmpty()) {
            autoSelectFirstCategory();
        }
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_layout, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoryList category = categoryList.get(position);

        try {
            holder.CategoryTitle.setText(cryptoUtil.decrypt(category.getCategory_name()));
        } catch (Exception e) {
            holder.CategoryTitle.setText("Unknown");
        }

        // Background Change
        if (selectedPosition == position) {
            holder.item_click.setBackgroundResource(R.drawable.category_selected_background);
        } else {
            holder.item_click.setBackgroundResource(R.drawable.category_backgournd);
        }

        holder.item_click.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            SubCategoryRecyclerView.setVisibility(View.VISIBLE);

            try {
                String categoryId = cryptoUtil.decrypt(category.getCategory_id());
                listener.onCategoryClick(categoryId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }


    // Auto select first category and trigger listener
    private void autoSelectFirstCategory() {
        try {
            String firstCategoryId = cryptoUtil.decrypt(categoryList.get(0).getCategory_id());
            SubCategoryRecyclerView.setVisibility(View.VISIBLE);
            listener.onCategoryClick(firstCategoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CategoryHolder extends RecyclerView.ViewHolder {
        TextView CategoryTitle;
        LinearLayout item_click;

        CategoryHolder(@NonNull View itemView) {
            super(itemView);
            CategoryTitle = itemView.findViewById(R.id.CategoryTitle);
            item_click = itemView.findViewById(R.id.item_click);
        }
    }
}
