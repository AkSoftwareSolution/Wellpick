package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aksoftwaresolution.wellpick.CategoryAdapter;
import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.UserAdapter;
import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.User;
import com.aksoftwaresolution.wellpick.model.UserModel;
import com.aksoftwaresolution.wellpick.presenter.UserPresenter;

import java.util.List;


public class HomeFragment extends Fragment implements UserContract.View {
    private RecyclerView recyclerView,CategoryRecyclerview;
    private UserPresenter presenter;
    private UserAdapter adapter;
    private CategoryAdapter categoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View homeView=inflater.inflate(R.layout.fragment_home, container, false);
        if (container!=null)container.removeAllViews();

        recyclerView=homeView.findViewById(R.id.popularImages);
        CategoryRecyclerview=homeView.findViewById(R.id.CategoryRecyclerview);

        presenter=new UserPresenter(this,new UserModel(getContext()));
        presenter.getPopular();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        CategoryRecyclerview.setLayoutManager(layoutManager1);






        return homeView;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetUsersSuccess(List<User> users) {

        adapter=new UserAdapter(users,getContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onGetCategorySuccess(List<CategoryList> categoryLists) {
        categoryAdapter=new CategoryAdapter(categoryLists,getContext());
        CategoryRecyclerview.setAdapter(categoryAdapter);

    }

    @Override
    public void onGetUsersFailure(String error) {
        Log.d("error",error);
    }

    @Override
    public void onGetCategoryFailure(String error) {
        Log.d("error",error);

    }
}