package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArrayAdapter extends android.widget.ArrayAdapter<String> {
    private ArrayList<String> categories;


    public ArrayAdapter(Context context, int resource, ArrayList<String> categories) {
        super(context, resource, categories);
        this.categories = categories;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_grid, parent, false);
        }

        String current_category = categories.get(position);

        TextView titleCat = convertView.findViewById(R.id.titleCat);
        titleCat.setText(current_category);

        return convertView;
    }

}
