package com.aksoftwaresolution.wellpick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.User;
import com.aksoftwaresolution.wellpick.model.UserModel;
import com.aksoftwaresolution.wellpick.presenter.UserPresenter;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements UserContract.View {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private UserPresenter presenter;
    private UserAdapter adapter;

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
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter=new UserPresenter(this,new UserModel(this));
        presenter.getUsers();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(GONE);

    }

    @Override
    public void onGetUsersSuccess(List<User> users) {
        adapter=new UserAdapter(users);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onGetUsersFailure(String error) {
        Toast.makeText(this,"Error"+error,Toast.LENGTH_LONG).show();

    }
}