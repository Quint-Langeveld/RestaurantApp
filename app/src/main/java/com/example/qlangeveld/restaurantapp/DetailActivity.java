package com.example.qlangeveld.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    MenuItem currentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = new Intent(getIntent());
        currentMenuItem = (MenuItem) intent.getSerializableExtra("clicked_MenuItem");

        TextView titel = findViewById(R.id.detailTitel);
        ImageView image = findViewById(R.id.detailImage);
        TextView description = findViewById(R.id.detailDescription);
        TextView price = findViewById(R.id.detailPrice);

        titel.setText(currentMenuItem.getName());

        String imageUrl = currentMenuItem.getImageUrl();
        Picasso.with(this).load(imageUrl).into(image);

        description.setText(currentMenuItem.getDescription());

        int Price = currentMenuItem.getPrice();
        String stringPrices = Integer.toString(Price);
        String stringPrice = "€ " + stringPrices + ",-";
        price.setText(stringPrice);
    }
}
