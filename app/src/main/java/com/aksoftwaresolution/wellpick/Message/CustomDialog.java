package com.aksoftwaresolution.wellpick.Message;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.model.LoginPreferences;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CustomDialog {
    public static void showLogoutDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.logout_dialog, null);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        AppCompatButton clear = view.findViewById(R.id.clear);
        AppCompatButton logout = view.findViewById(R.id.logout);

        TextView dialog_title = view.findViewById(R.id.dialog_title);
        TextView dialog_message = view.findViewById(R.id.dialog_message);
        dialog_title.setText("Logout");
        dialog_message.setText("Are you sure you want to logout?");

        LoginPreferences loginPreferences=new LoginPreferences(context);

        // Clear data
        clear.setOnClickListener(v -> {
            dialog.dismiss();
        });

        // Logout
        logout.setOnClickListener(v -> {
            loginPreferences.logout();
            dialog.dismiss();
        });

        // Set background color to transparent
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }//logout


     public static void showDeleteAccountDialog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.delete_account,null);;

        builder.setView(view);
        AlertDialog dialog=builder.create();
        AppCompatButton deleteClear = view.findViewById(R.id.deleteClear);
        AppCompatButton DeleteAccount = view.findViewById(R.id.DeleteAccount);

        TextView delete_title = view.findViewById(R.id.dialog_title);
        TextView delete_message = view.findViewById(R.id.dialog_message);
        delete_title.setText("Delete Account");
        delete_message.setText("Are you sure you want to delete your account?\n This action cannot be undone.");


        deleteClear.setOnClickListener(v -> {
            dialog.dismiss();
        });









        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }
    public static void showEditDialog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.edit_layout,null);;

        builder.setView(view);
        AlertDialog dialog=builder.create();
        AppCompatButton updateInfo = view.findViewById(R.id.updateInfo);
        TextInputEditText edName=view.findViewById(R.id.edName);
        TextInputEditText edEmail=view.findViewById(R.id.edEmail);

        TextView delete_title = view.findViewById(R.id.dialog_title);
        delete_title.setText("Edit Info !");












        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }


}
