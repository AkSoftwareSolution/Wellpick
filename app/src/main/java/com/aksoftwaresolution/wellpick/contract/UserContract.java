package com.aksoftwaresolution.wellpick.contract;

import com.aksoftwaresolution.wellpick.model.CategoryList;
import com.aksoftwaresolution.wellpick.model.MultipleItemList;
import com.aksoftwaresolution.wellpick.model.User;

public interface UserContract {

    interface View {
        void showLoading();
        void hideLoading();
        void onGetUsersSuccess(java.util.List<User> users);
        void onGetCategorySuccess(java.util.List<CategoryList> users);
        void onGetMultipleSuccess(java.util.List<MultipleItemList> multipleItemLists);
        void onGetMultipleFailure(String error);
        void onGetUsersFailure(String error);
        void onGetCategoryFailure(String error);
    }

    interface Presenter {
        void getPopular();

    }

    interface Model {
        void getPopularImages(OnFinishedListener listener);
        void getCategory(OnCategoryFinishedListener listener);
        void getMultipleItem(OnMultipleFinishedListener listener);

        interface OnFinishedListener {
            void onFinished(java.util.List<User> users);
            void onFailure(String error);

        }

      interface OnCategoryFinishedListener{
          void OnCategoryFinished(java.util.List<CategoryList> categoryLists);
          void onCategoryFailure(String error);
      }

        interface OnMultipleFinishedListener{
            void OnMultipleFinished(java.util.List<MultipleItemList> multipleItemLists);
            void onMultipleFailure(String error);
        }

    }
}

