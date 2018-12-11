package com.example.qlangeveld.restaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    ArrayList<MenuItem> categories;
    MenuRequest menuRequest;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuRequest = new MenuRequest(this);
        menuRequest.getMenu(this);

        Intent intent = new Intent(getIntent());
        category = (String) intent.getSerializableExtra("clickedCategory");

        ListView listView = findViewById(R.id.listViewMenu);
        listView.setOnItemClickListener(new ListItemClickListener());
    }


    @Override
    public void gotMenu(ArrayList<MenuItem> myCategories) {
        this.categories = myCategories;
        Log.d("size of categories list", "gotMenu: " + categories.size());

        int lengthCatagories = categories.size();
        // and fill with the right menuItems
        for (int i=0; i < categories.size(); i++) {
            Log.d("i", "gotMenu: " + i);
            Log.d("next item", "gotMenu: " + categories.get(i).getName());

            MenuItem currentMenuItem = categories.get(i);

            if (!(currentMenuItem.getCategory().equals(category))) {
                categories.remove(currentMenuItem);
                Log.d("moet ik niet", "gotMenu: ");
                i -= 1;
            } else {
                Log.d("vind ik lekker", "gotMenu: ");
            }
        }

        Log.d("en nu de lengte van categories", "gotMenu: " + categories.size());

        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.menu_grid, categories);
        ListView listView = findViewById(R.id.listViewMenu);
        listView.setAdapter(menuAdapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem clickedMenuItem = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("clicked_MenuItem", clickedMenuItem);
            startActivity(intent);

        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        menuRequest.getMenu(this);
//    }
}
