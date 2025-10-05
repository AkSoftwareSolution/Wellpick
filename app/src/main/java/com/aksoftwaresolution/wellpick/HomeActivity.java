package com.aksoftwaresolution.wellpick;

import static android.view.Gravity.START;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.aksoftwaresolution.wellpick.FragmentManager.ExploreFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.HomeFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.PremiumFragment;
import com.aksoftwaresolution.wellpick.FragmentManager.Profile_Fragment;
import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.User;
import com.aksoftwaresolution.wellpick.model.UserModel;
import com.aksoftwaresolution.wellpick.presenter.UserPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity  {
     DrawerLayout drawerlayout;
     NavigationView navigationView;
     BottomNavigationView bottomNavigationView;
     ImageView homeIcon;

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

        setFragmentManager(new HomeFragment());

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
                    setFragmentManager(new HomeFragment());
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




}///***************************************public class here *************************************************************************************************************