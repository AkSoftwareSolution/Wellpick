package com.aksoftwaresolution.wellpick.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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

import com.aksoftwaresolution.wellpick.FragmentManager.AboutFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.ExploreFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.HomeFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.PremiumFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.Profile_Fragment;
import com.aksoftwaresolution.wellpick.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity  {
   private DrawerLayout drawerlayout;
   private NavigationView navigationView;
   private BottomNavigationView bottomNavigationView;
   private ImageView homeIcon,profileIcon,premiumIcon;

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

        // All Element Introduce by Id
        drawerlayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.NavigationView);
        bottomNavigationView=findViewById(R.id.bottomnavigation);
        homeIcon=findViewById(R.id.homeIcon);
        profileIcon=findViewById(R.id.profileIcon);
        premiumIcon=findViewById(R.id.premiumIcon);



        //Go to the profile fragment onClickListener here
        profileIcon.setOnClickListener(v -> {
           setFragmentManager(new Profile_Fragment());

        });

        //Subscription massage bottomSheet dialog
        premiumIcon.setOnClickListener(v -> {
           // SubscriptionMessage.showSubscriptionDialog(this);
            startActivity(new Intent(HomeActivity.this, SubscriptionActivity.class));

        });

        //called by all mathord
        setFragmentManager(new HomeFragment());
        askNotificationPermission();
        FirebaseInit();

        //open drawerlayout navigationView onclick here
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