package com.aksoftwaresolution.wellpick.presenter;

import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.User;

import java.util.List;

public class UserPresenter implements UserContract.Presenter,UserContract.Model.OnFinishedListener, UserContract.Model.OnCategoryFinishedListener {
    private UserContract.View view;
    private UserContract.Model model;
    public UserPresenter(UserContract.View view, UserContract.Model model) {
        this.view = view;
        this.model = model;
    }
    @Override
    public void onFinished(List<User> users) {

        if (view!=null){
            view.hideLoading();
            view.onGetUsersSuccess(users);
        }

    }

    @Override
    public void onFailure(String error) {
        if (view!=null){
            view.hideLoading();
            view.onGetUsersFailure(error);
        }

    }

    @Override
    public void getPopular() {

        if (view!=null){
            view.showLoading();
        }
        model.getPopularImages(this);
        model.getCategory(this);


    }

    @Override
    public void OnCategoryFinished(List<CategoryList> categoryLists) {
        if (view!=null){
            view.hideLoading();
            view.onGetCategorySuccess(categoryLists);
        }

    }

    @Override
    public void onCategoryFailure(String error) {
        if (view!=null){
            view.hideLoading();
            view.onGetCategoryFailure(error);
        }

    }
}
