package com.aksoftwaresolution.wellpick.contract;

import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.User;

public interface UserContract {

    interface View {
        void showLoading();
        void hideLoading();
        void onGetUsersSuccess(java.util.List<User> users);
        void onGetCategorySuccess(java.util.List<CategoryList> users);
        void onGetUsersFailure(String error);
        void onGetCategoryFailure(String error);
    }

    interface Presenter {
        void getPopular();

    }

    interface Model {
        void getPopularImages(OnFinishedListener listener);
        void getCategory(OnCategoryFinishedListener listener) ;

        interface OnFinishedListener {
            void onFinished(java.util.List<User> users);
            void onFailure(String error);

        }

      interface OnCategoryFinishedListener{
          void OnCategoryFinished(java.util.List<CategoryList> categoryLists);
          void onCategoryFailure(String error);
      }

    }
}

