package com.aksoftwaresolution.wellpick.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.contract.ConformPasswordContract;
import com.aksoftwaresolution.wellpick.contract.ConformPasswordPresenterImpl;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ConformPasswordActivity extends AppCompatActivity implements ConformPasswordContract.View {
    ImageView icon_back;
    TextInputEditText etNewPassword, etConfirmPassword;
    TextInputLayout layoutNewPassword, layoutConfirmPassword;
    MaterialButton btnConfirm;

    ConformPasswordContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conform_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();

        icon_back.setOnClickListener(v -> {
            onBackPressed();
        });

        presenter = new ConformPasswordPresenterImpl(this);



        btnConfirm.setOnClickListener(v -> presenter.validatePassword(
                etNewPassword.getText().toString().trim(),
                etConfirmPassword.getText().toString().trim()
        ));




    }
    private void initView() {
        icon_back=findViewById(R.id.icon_back);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        layoutNewPassword = findViewById(R.id.layoutNewPassword);
        layoutConfirmPassword = findViewById(R.id.layoutConfirmPassword);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    @Override
    public void showNewPasswordError(String message) {
        layoutNewPassword.setError(message);

    }

    @Override
    public void showConfirmPasswordError(String message) {
        layoutConfirmPassword.setError(message);

    }

    @Override
    public void clearErrors() {
        layoutNewPassword.setError(null);
        layoutConfirmPassword.setError(null);

    }

    @Override
    public void onPasswordSuccess() {
        Toast.makeText(this, "Password set successfully", Toast.LENGTH_SHORT).show();
        // TODO: API call / navigate
        // finish();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}