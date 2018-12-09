package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.view.textclassifier.TextLinks;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    private Context myContext;
    private ArrayList<MenuItem> ArrayListMenu = new ArrayList<>();

    public void getMenu(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(myContext);
        String url = "https://resto.mprog.nl/menu";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(String response) {
                onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onErrorResponse(error);
            }
        });

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
