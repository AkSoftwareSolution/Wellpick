package com.aksoftwaresolution.wellpick.presenter;

import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.MultipleItemList;
import com.aksoftwaresolution.wellpick.model.SubCategory;
import com.aksoftwaresolution.wellpick.model.User;

import java.util.List;

public class UserPresenter implements UserContract.Presenter,UserContract.Model.OnFinishedListener,
        UserContract.Model.OnCategoryFinishedListener,
        UserContract.Model.OnSubCategoriesFinishedListener,UserContract.Model.OnPopularFinishedListener,
        UserContract.Model.OnPremiumFinishedListener {
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
    public void loadSubCategories(String categoryId) {
        if (view!=null){
            view.showLoading();
           model.SubCategoriesData(categoryId,this);
        }

    }



    @Override
    public void loadPopularImages() {
        if (view!=null){
            view.showLoading();
            model.getPopularItemImages(this);
        }

    }

    @Override
    public void loadPremiumImages() {
        if (view!=null){
            view.showLoading();
            model.getPremiumImages(this);
        }

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

    @Override
    public void onSubCategoriesFinished(List<SubCategory> subCategories) {

        if (view!=null){
            view.hideLoading();
            view.onGetSubCategorySuccess(subCategories);
        }


    }

    @Override
    public void onSubCategoriesFailure(String error) {
        if (view!=null){
            view.hideLoading();
            view.onGetSubCategoryFailure(error);
        }


    }

    @Override
    public void onPopularFinished(List<MultipleItemList> PopularItemLists) {
        if (view!=null){
            view.hideLoading();
            view.onGetPopularSuccess(PopularItemLists);
        }



    }

    @Override
    public void onPopularFailure(String error) {
        if (view!=null){
            view.hideLoading();
            view.onGetPopularFailure(error);
        }


    }

    @Override
    public void onPremiumFinished(List<MultipleItemList> PremiumLists) {
        if (view!=null){
            view.hideLoading();
            view.onGetPremiumSuccess(PremiumLists);
        }

    }

    @Override
    public void onPremiumFailure(String error) {
        if (view!=null){
            view.hideLoading();
            view.onGetPremiumFailure(error);
        }


    }
}
