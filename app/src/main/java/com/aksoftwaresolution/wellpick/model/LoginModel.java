package com.aksoftwaresolution.wellpick.model;

import android.content.Context;

import androidx.annotation.Nullable;

import com.aksoftwaresolution.wellpick.CryptoUtil.CryptoUtil;
import com.aksoftwaresolution.wellpick.contract.LoginContract;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginModel implements LoginContract.LoginModel {

    private final Context context;
    private final CryptoUtil cryptoUtil = new CryptoUtil();    // 🔹 তোমার সার্ভারের API URL বসাও
    private final String loginRequestUrl = "https://yourdomain.com/api/login.php";
    private final String signupRequestUrl = "https://yourdomain.com/api/signup.php";

    public LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public void doLogin(String loginName, String loginPassword, OnLoginFinishedListener listener) {
        StringRequest loginRequest = new StringRequest(Request.Method.POST, loginRequestUrl,
                response -> listener.onSuccess(response),
                error -> listener.onFailure(error.getMessage())) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> loginMap = new HashMap<>();
                try {
                    loginMap.put("loginEmail", cryptoUtil.encrypt(loginName));
                    loginMap.put("loginPassword", cryptoUtil.encrypt(loginPassword));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return loginMap;
            }
        };

        Volley.newRequestQueue(context).add(loginRequest);
    }

    @Override
    public void signUpUser(String name, String email, String password, OnSignUpListener listener) {
        StringRequest signupRequest = new StringRequest(Request.Method.POST, signupRequestUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       LoginPreferences preferences = new LoginPreferences(context);
                       preferences.saveLoginSession(email);

                        listener.onSignUpSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onSignUpFailure(error.getMessage());
                    }
                }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> signupMap = new HashMap<>();
                try {
                    signupMap.put("name", cryptoUtil.encrypt(name));
                    signupMap.put("email", cryptoUtil.encrypt(email));
                    signupMap.put("password", cryptoUtil.encrypt(password));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return signupMap;
            }
        };

        Volley.newRequestQueue(context).add(signupRequest);
    }
}
