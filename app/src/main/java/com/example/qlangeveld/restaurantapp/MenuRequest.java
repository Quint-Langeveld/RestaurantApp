package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private ArrayList<MenuItem> ArrayListMenuItems = new ArrayList<> ();
    private Context myContext;


    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }


    public MenuRequest(Context myContext) {
        this.myContext = myContext;
    }

    private Callback activity;
    public void getMenu(final Callback activity) {
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(myContext);
        String url = "https://resto.mprog.nl/menu";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotMenuError(message);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray JSONArrayCategories = new JSONArray();
        try {
            JSONArrayCategories = response.getJSONArray("items");
        } catch (JSONException e) {
            String message = e.getMessage();
            activity.gotMenuError(message);
        }

        for (int i=0; i < JSONArrayCategories.length(); i++) {
            JSONObject newMenuItem;

            try {
                newMenuItem = JSONArrayCategories.getJSONObject(i);

                String name = newMenuItem.getString("name");
                String description = newMenuItem.getString("description");
                String imageUrl = newMenuItem.getString("image_url");
                String category = newMenuItem.getString("category");
                int price = newMenuItem.getInt("price");

                MenuItem currentMenuItem = new MenuItem(name, description, imageUrl, category, price);
                ArrayListMenuItems.add(currentMenuItem);
            }
            catch (JSONException e) {
                String message = e.getMessage();
                activity.gotMenuError(message);
            }

        }
        activity.gotMenu(ArrayListMenuItems);
    }
}
