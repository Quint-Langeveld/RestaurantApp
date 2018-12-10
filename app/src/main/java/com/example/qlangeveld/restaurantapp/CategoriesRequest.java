package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.telecom.Call;
import android.widget.Toast;

import com.android.volley.Request;
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

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    private Context myContext;
    public ArrayList<String> ArrayListCategories = new ArrayList<>();


    // the constructor
    public CategoriesRequest(Context context) {
         myContext = context;
    }

    private Callback activity;
    public void getCategories(final Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(myContext);
        String url = "https://resto.mprog.nl/categories";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotCategoriesError(message);
    }


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
    }
}
