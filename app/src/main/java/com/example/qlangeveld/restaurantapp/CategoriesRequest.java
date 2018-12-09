package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.telecom.Call;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context myContext;
    private ArrayList<String> ArrayListCategories = new ArrayList<>();
    private Callback activity = new Callback() {
        @Override
        public void gotCategories(ArrayList<String> categories) {
        }

        @Override
        public void gotCategoriesError(String message) {
        }
    };


    // the constructor
    public CategoriesRequest(Context context) {
         myContext = context;
    }


    public void getCategories(final Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(myContext);
        String url = "https://resto.mprog.nl";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                    new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray JSONArrayCategories = response.getJSONArray("categories");

                    for (int i=0; i < JSONArrayCategories.length(); i++) {
                        try {
                            ArrayListCategories.add(JSONArrayCategories.getString(i));
                        }
                        catch (JSONException e) {}
                    }
                }
                catch (JSONException e) {}

                activity.gotCategories(ArrayListCategories);
                getCategories(activity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = error.getMessage();
                activity.gotCategoriesError(message);
            }
        });

        queue.add(jsonObjectRequest);
    }


    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
    }


    @Override
    public void onResponse(JSONObject response) {
    }
}
