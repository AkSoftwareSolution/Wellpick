package com.aksoftwaresolution.wellpick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.model.MultipleItemList;

import java.util.List;

public class MultipleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMAGE = 1;

    private List<MultipleItemList> itemList;

    public MultipleAdapter(List<MultipleItemList> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = itemList.get(position);
        if (item instanceof String) {
            return TYPE_TEXT;
        } else {
            return TYPE_IMAGE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_TEXT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_layout, parent, false);
            return new TextViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user, parent, false);
            return new ImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder= (TextViewHolder) holder;
        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder= (ImageViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.CategoryTitle);
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ItemImages);
        }
    }
}
