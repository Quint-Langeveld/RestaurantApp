package com.example.qlangeveld.restaurantapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends android.widget.ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> MenuItemArrayList;

    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.MenuItemArrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_grid, parent, false);
        }

        MenuItem currentMenuItem = MenuItemArrayList.get(position);


        String imageUrl = currentMenuItem.getImageUrl();
        ImageView imageView = convertView.findViewById(R.id.menuImage);
        Picasso.with(this.getContext()).load(imageUrl).into(imageView);


        TextView menuTitle = convertView.findViewById(R.id.menuTitle);
        TextView menuPrice = convertView.findViewById(R.id.menuPrice);

        int price = currentMenuItem.getPrice();
        String Price = Integer.toString(price);


        menuTitle.setText(currentMenuItem.getName());
        menuPrice.setText(Price);


        return convertView;
    }
}
