package com.aksoftwaresolution.wellpick.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aksoftwaresolution.wellpick.R;

public class CustomToast {
    public static void showCustomToast(Context context, String message) {
        // Inflate layout
        LayoutInflater inflater =LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast_layout, null);
        // Find views
        TextView text = layout.findViewById(R.id.toast_text);
        ImageView icon = layout.findViewById(R.id.toast_icon);        // Set values
        text.setText(message);
        icon.setImageResource(R.drawable.img_4);
        // Create Toast
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
