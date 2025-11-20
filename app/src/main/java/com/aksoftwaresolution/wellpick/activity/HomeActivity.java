package com.aksoftwaresolution.wellpick.activity;

import static android.view.Gravity.START;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.CryptoUtil.CryptoUtil;
import com.aksoftwaresolution.wellpick.FragmentManager.AboutFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.ExploreFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.HomeFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.PremiumFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.Profile_Fragment;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.User;
import com.aksoftwaresolution.wellpick.model.UserModel;
import com.aksoftwaresolution.wellpick.presenter.UserPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class HomeActivity extends AppCompatActivity  {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ImageView homeIcon,profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        drawerlayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.NavigationView);
        bottomNavigationView=findViewById(R.id.bottomnavigation);
        homeIcon=findViewById(R.id.homeIcon);
        profileIcon=findViewById(R.id.profileIcon);

        setFragmentManager(new HomeFragment());
        askNotificationPermission();
        FirebaseInit();

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.homeIcon){
                    setFragmentManager(new HomeFragment());
                    drawerlayout.closeDrawer(GravityCompat.START);
                }else if (menuItem.getItemId()==R.id.exploreIcon){
                    setFragmentManager(new ExploreFragment());
                    drawerlayout.closeDrawer(GravityCompat.START);
                }else if (menuItem.getItemId()==R.id.premiumIcon){
                    setFragmentManager(new PremiumFragment());
                    drawerlayout.closeDrawer(GravityCompat.START);
                }else if (menuItem.getItemId()==R.id.profileIcon){
                    setFragmentManager(new Profile_Fragment());
                    drawerlayout.closeDrawer(GravityCompat.START);
                }else if (menuItem.getItemId()==R.id.AboutIcon){
                    setFragmentManager(new AboutFragment());
                    drawerlayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId()==R.id.homeIcon){
                    setFragmentManager(new HomeFragment());
                }else if (menuItem.getItemId()==R.id.exploreIcon){
                    setFragmentManager(new ExploreFragment());
                }else if (menuItem.getItemId()==R.id.premiumIcon){
                    setFragmentManager(new PremiumFragment());
                }else if (menuItem.getItemId()==R.id.profileIcon){
                    setFragmentManager(new Profile_Fragment());
                }





                return true ;
            }
        });









    }///********************************onCreate bundle end here*********************************************************************************************************

    public void setFragmentManager(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.FrameLayout,fragment);
        transaction.commit();
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    public void FirebaseInit(){
        // Inside onCreate() or any method:
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        String token=task.getResult();
                        Log.w("FCM_TOKENf", "Fetching FCM registration token failed"+ token);
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d("FCM_TOKEN", "Current token: " + token);
                });
    }

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }


}///***************************************public class here *************************************************************************************************************