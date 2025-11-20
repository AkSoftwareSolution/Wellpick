package com.aksoftwaresolution.wellpick.contract;

import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.SubCategory;
import com.aksoftwaresolution.wellpick.model.User;

import java.util.List;

public interface UserContract {

    interface View {
        void showLoading();
        void hideLoading();
        void onGetUsersSuccess(java.util.List<User> users);
        void onGetCategorySuccess(java.util.List<CategoryList> users);
        void onGetSubCategorySuccess(java.util.List<SubCategory> subCategories);
        void onGetPremiumSuccess(List<SubCategory> itemLists);
        void onGetPremiumFailure(String error);
        void onGetUsersFailure(String error);
        void onGetCategoryFailure(String error);
        void onGetSubCategoryFailure(String error);
    }

    interface Presenter {
        void getPopular();
        void loadSubCategories(String categoryId);
        void loadPremiumImages();

    }

     interface Model {
        void getPopularImages(OnFinishedListener listener);
        void getCategory(OnCategoryFinishedListener listener);
        void SubCategoriesData(String categoryId,OnSubCategoriesFinishedListener onSubCategoriesFinishedListener);
        void getPremiumImages(OnPremiumFinishedListener onPremiumFinishedListener);


        interface OnPremiumFinishedListener {
            void onPremiumFinished(List<SubCategory> PremiumLists);
            void onPremiumFailure(String error);

        }
        interface OnFinishedListener {
            void onFinished(java.util.List<User> users);
            void onFailure(String error);

        }
        interface OnSubCategoriesFinishedListener {
            void onSubCategoriesFinished(java.util.List<SubCategory> subCategories);
            void onSubCategoriesFailure(String error);

        }

      interface OnCategoryFinishedListener{
          void OnCategoryFinished(java.util.List<CategoryList> categoryLists);
          void onCategoryFailure(String error);
      }



    }
}

