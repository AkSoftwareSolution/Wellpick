package com.aksoftwaresolution.wellpick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.model.User;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private List<User> userList;
    Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context=context;
        Collections.shuffle(userList);

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);


        Glide.with(context).load("https://wellpick.xyz/wellpickAdmin/popular/"+user.getEncodeImage()).into(holder.ItemImages);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView ItemImages;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemImages = itemView.findViewById(R.id.ItemImages);

        }
    }
}
