package com.aksoftwaresolution.wellpick.model;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreferences {
    //SharedPreferences for Login
    private static final String PREF_NAME = "user_session";
    //Key for SharedPreferences
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    //Key for SharedPreferences
    private static final String KEY_EMAIL = "email";
    //SharedPreferences Instance
    private final SharedPreferences sharedPreferences;
    //Editor for SharedPreferences
    private final SharedPreferences.Editor editor;
    //Constructor

    public LoginPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    //LoginSaved Data successful
    public void saveLoginSession(String email) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    //Chack LoggedIn or not
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    //user logout here
    public void logout() {
        editor.clear();
        editor.apply();
    }
    // getUserEmail here
    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }
}
