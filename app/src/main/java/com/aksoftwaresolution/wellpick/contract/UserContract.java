package com.aksoftwaresolution.wellpick.contract;

import com.aksoftwaresolution.wellpick.model.User;

public interface UserContract {

    interface View {
        void showLoading();
        void hideLoading();
        void onGetUsersSuccess(java.util.List<User> users);
        void onGetUsersFailure(String error);
    }

    interface Presenter {
        void getUsers();
    }

    interface Model {
        void fetchUsers(OnFinishedListener listener);

        interface OnFinishedListener {
            void onFinished(java.util.List<User> users);
            void onFailure(String error);
        }
    }
}

