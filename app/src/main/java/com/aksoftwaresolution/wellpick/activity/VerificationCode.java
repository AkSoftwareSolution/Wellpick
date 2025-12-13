package com.aksoftwaresolution.wellpick.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aksoftwaresolution.wellpick.R;
import com.google.android.material.textfield.TextInputEditText;

public class VerificationCode extends AppCompatActivity {

    TextView tvSendOptEmail;
    TextInputEditText code1, code2, code3, code4, code5, code6;
    AppCompatButton verifyOtp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verification_code);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




       code1=findViewById(R.id.code1);
       code2=findViewById(R.id.code2);
       code3=findViewById(R.id.code3);
       code4=findViewById(R.id.code4);
       code5=findViewById(R.id.code5);
       code6=findViewById(R.id.code6);
       verifyOtp_btn=findViewById(R.id.verifyOtp_btn);
       tvSendOptEmail=findViewById(R.id.tvSendOptEmail);

       // OTP Field Watcher and Button Enable/Disable
       setupOtpWatcher(code1);
       setupOtpWatcher(code2);
       setupOtpWatcher(code3);
       setupOtpWatcher(code4);
       setupOtpWatcher(code5);
       setupOtpWatcher(code6);

       // OTP Field Watcher and Button Enable/Disable

       moveToNext(code1, code2);
       moveToNext(code2, code3);
       moveToNext(code3, code4);
       moveToNext(code4, code5);
       moveToNext(code5, code6);


       moveBack(code2, code1);
       moveBack(code3, code2);
       moveBack(code4, code3);
       moveBack(code5, code4);
       moveBack(code6, code5);
       // OTP Field Watcher and Button Enable/Disable













    }// onCreate

    // OTP Field Watcher and Button Enable/Disable

    private void checkOtpFilled() {
        String d1 = code1.getText().toString().trim();
        String d2 = code2.getText().toString().trim();
        String d3 = code3.getText().toString().trim();
        String d4 = code4.getText().toString().trim();
        String d5 = code5.getText().toString().trim();
        String d6 = code6.getText().toString().trim();

        if (!d1.isEmpty() && !d2.isEmpty() && !d3.isEmpty() &&
                !d4.isEmpty() && !d5.isEmpty() && !d6.isEmpty()) {

            verifyOtp_btn.setEnabled(true);
            verifyOtp_btn.setBackgroundResource(R.drawable.btn_bg);  // active bg

        } else {
            verifyOtp_btn.setEnabled(false);
            verifyOtp_btn.setBackgroundResource(R.drawable.verify_bag); // disable bg
        }
    }// checkOtpFilled

// OTP Field Watcher and Button Enable/Disable
    private void setupOtpWatcher(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkOtpFilled();   // check if OTP fields are filled
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }// setupOtpWatcher
// OTP Field Watcher and Button Enable/Disable


    // OTP Field Watcher and Button Enable/Disable
    private void moveToNext(EditText current, EditText next) {
        current.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    next.requestFocus();
                }
            }

            @Override public void afterTextChanged(Editable s) {}
        });
    }// moveToNext

    // OTP Field Watcher and Button Enable/Disable
    private void moveBack(EditText current, EditText previous) {
        current.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                if (current.getText().toString().isEmpty()) {
                    previous.requestFocus();   //
                }
            }
            return false;
        });
    }
//

}