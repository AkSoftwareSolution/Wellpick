package com.aksoftwaresolution.wellpick.model;

import android.content.Context;
import android.util.Log;
import com.aksoftwaresolution.wellpick.CryptoUtil.CryptoUtil;
import com.aksoftwaresolution.wellpick.contract.UserContract;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class UserModel implements UserContract.Model {
    private Context context;
    CryptoUtil cryptoUtil=new CryptoUtil();
    private static final String url="https://wellpick.xyz/wellpick/ViewPopularImages.php";
    private static final String CategoryUrl="https://www.wellpick.xyz/wellpick/CategoreyPostView.php";
    private static final String SubCategoryUrl="https://wellpick.xyz/wellpickAdmin/SubCategory.php";
    public UserModel(Context context){
        this.context=context;

    }
    @Override
    public void getPopularImages(OnFinishedListener listener) {

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();


            try {
                jsonObject.put("password",cryptoUtil.encrypt("loadPopularImages"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            jsonArray.put(jsonObject);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<User> userList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object=response.getJSONObject(i);

                                String id = object.getString("id");
                                String name = object.getString("name");
                                String encodeImage = object.getString("encodeImage");
                                String description = object.getString("description");

                                Log.d("response",id+name+encodeImage+description);

                                userList.add(new User(id, name, encodeImage,description));

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

    @Override
    public void getCategory(OnCategoryFinishedListener listener) {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("password",cryptoUtil.encrypt("LodeCategoryData"));
            jsonArray.put(jsonObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.POST,CategoryUrl, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                List<CategoryList>category=new ArrayList<>();

                for (int x=0; x<jsonArray.length();x++){
                    try {
                        JSONObject object=jsonArray.getJSONObject(x);

                        String category_id=object.getString("id");
                        String category_name=object.getString("category_name");

                        category.add(new CategoryList(category_id,category_name));



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                listener.OnCategoryFinished(category);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onCategoryFailure(volleyError.toString());

            }
        });

      Volley.newRequestQueue(context).add(arrayRequest);


    }
    @Override
    public void SubCategoriesData(String categoryId, OnSubCategoriesFinishedListener onSubCategoriesFinishedListener) {

        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("password", "loadPopularImages");
            jsonObject.put("categoryId", categoryId); // তোমার categoryId অনুযায়ী বসাও
            jsonArray.put(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<SubCategory>subCategories=new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                SubCategoryUrl,
                jsonArray,
                response -> {
                    // Response সফলভাবে এলে এখানে আসবে
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            int id = obj.getInt("id");
                            String name = obj.getString("name");
                            String imageUrl = obj.getString("image_url");
                            subCategories.add(new SubCategory(id,name,imageUrl));

                            Log.d("ImageData", "Name: " + name + ", Image: " + imageUrl);
                            // এখান থেকে RecyclerView এ পাঠাও
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   onSubCategoriesFinishedListener.onSubCategoriesFinished(subCategories);

                },
                error -> {
                    // Error হলে এখানে আসবে
                    if (error.networkResponse != null) {
                        Log.e("VolleyError", "Status Code: " + error.networkResponse.statusCode);
                    }
                    Log.e("VolleyError", "Message: " + error.getMessage());
                    onSubCategoriesFinishedListener.onSubCategoriesFailure(error.toString());
                }
        );

// Request Queue তে যোগ করা
        Volley.newRequestQueue(context).add(jsonArrayRequest);

    }

    @Override
    public void getPremiumImages(OnPremiumFinishedListener onPremiumFinishedListener) {

    }


}
