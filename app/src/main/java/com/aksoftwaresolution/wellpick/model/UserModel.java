package com.aksoftwaresolution.wellpick.model;

import android.content.Context;

import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UserModel implements UserContract.Model {
    private Context context;
    private static final String url="https://jsonplaceholder.typicode.com/users";
    public UserModel(Context context){
        this.context=context;

    }
    @Override
    public void fetchUsers(OnFinishedListener listener) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<User> userList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                String name = response.getJSONObject(i).getString("name");
                                String email = response.getJSONObject(i).getString("email");
                                String phone = response.getJSONObject(i).getString("phone");

                                userList.add(new User(name, email, phone));
                            }
                            listener.onFinished(userList);
                        } catch (JSONException e) {
                            listener.onFailure(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onFailure(error.toString());
                    }
                });


        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }
}
