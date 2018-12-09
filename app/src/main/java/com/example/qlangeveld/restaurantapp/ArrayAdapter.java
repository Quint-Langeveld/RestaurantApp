package com.example.qlangeveld.restaurantapp;

import android.content.Context;

import java.util.ArrayList;

public class ArrayAdapter extends android.widget.ArrayAdapter<String> {
    private ArrayList<String> categories;


    public ArrayAdapter(Context context, int resource, ArrayList<String> categories) {
        super(context, resource);
        this.categories = categories;
    }
}
