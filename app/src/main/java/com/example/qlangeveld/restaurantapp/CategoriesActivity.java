package com.example.qlangeveld.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

//    public ArrayList<String> cattegoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest categoriesRequest = new CategoriesRequest(this);
        categoriesRequest.getCategories(this);

        Toast.makeText(this, "Started", Toast.LENGTH_LONG).show();

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListItemClickListener());

        Log.d("iets", "onCreate: ");
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Log.d("rsto", "gotCategories: " + categories.size());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.category_grid, categories);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clickedCategory", category);
            startActivity(intent);
        }
    }
}
