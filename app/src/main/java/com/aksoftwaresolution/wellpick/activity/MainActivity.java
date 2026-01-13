package com.aksoftwaresolution.wellpick.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aksoftwaresolution.wellpick.R;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // com.aksoftwaresolution.wellpick.Message.InitMobileAds
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            // Android 12 বা তার উপরে হলে Splash না দেখিয়ে সরাসরি HomeActivity তে যাওয়া হবে
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        } else {
            // Android 12 এর নিচে হলে Splash screen দেখাবে
            setContentView(R.layout.activity_main); // তোমার splash layout

            int SPLASH_TIME_OUT = 2000; // উদাহরণ: ২ সেকেন্ড

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish(); // Splash শেষ হলে আর ফেরা যাবে না
                }
            }, SPLASH_TIME_OUT);
        }

    }
}