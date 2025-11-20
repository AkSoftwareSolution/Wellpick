package com.aksoftwaresolution.wellpick.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aksoftwaresolution.wellpick.R;
import com.aksoftwaresolution.wellpick.adapter.CategoryAdapter;
import com.aksoftwaresolution.wellpick.adapter.SubCategoryAdapter;
import com.aksoftwaresolution.wellpick.adapter.UserAdapter;
import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.SubCategory;
import com.aksoftwaresolution.wellpick.model.User;
import com.aksoftwaresolution.wellpick.model.UserModel;
import com.aksoftwaresolution.wellpick.presenter.UserPresenter;

import java.util.List;


public class HomeFragment extends Fragment implements UserContract.View {
    private RecyclerView recyclerView,CategoryRecyclerview,SubCategoryRecyclerView,AllItemRecyclerView;
    private UserPresenter presenter;
    private UserAdapter adapter;
    private CategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View homeView=inflater.inflate(R.layout.fragment_home, container, false);
        if (container!=null)container.removeAllViews();

        recyclerView=homeView.findViewById(R.id.popularImages);
        CategoryRecyclerview=homeView.findViewById(R.id.CategoryRecyclerview);
       SubCategoryRecyclerView=homeView.findViewById(R.id.SubCategoryRecyclerView);



        presenter=new UserPresenter(this,new UserModel(getContext()));
        presenter.getPopular();
        presenter.loadPremiumImages();


        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        SubCategoryRecyclerView.setLayoutManager(gridLayoutManager);
        CategoryRecyclerview.setLayoutManager(layoutManager1);

        // class-level variable








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
        categoryAdapter = new CategoryAdapter(categoryLists,SubCategoryRecyclerView, categoryId -> {
            presenter.loadSubCategories(categoryId);
        });
        CategoryRecyclerview.setAdapter(categoryAdapter);

    }

    @Override
    public void onGetSubCategorySuccess(List<SubCategory> subCategories) {
        subCategoryAdapter=new SubCategoryAdapter(subCategories,getContext());
        SubCategoryRecyclerView.setAdapter(subCategoryAdapter);
        SubCategoryRecyclerView.setAlpha(0f);
        SubCategoryRecyclerView.animate().alpha(1f).setDuration(300).start();


    }

    @Override
    public void onGetPremiumSuccess(List<SubCategory> itemLists) {


    }

    @Override
    public void onGetPremiumFailure(String error) {
        Log.d("error",error);

    }


    @Override
    public void onGetUsersFailure(String error) {

        Log.d("error",error);
    }

    @Override
    public void onGetCategoryFailure(String error) {
        Log.d("error",error);

    }

    @Override
    public void onGetSubCategoryFailure(String error) {
        Log.d("suberror",error);


    }
}