package com.example.cs246teamproject_cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class SearchingTool extends AppCompatActivity {
    RecipeAdapter recipeAdapter;
    recipeFileHandler handler;
    ListView listView;
    List<String> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_tool);


        handler = new recipeFileHandler(getApplicationContext().getFilesDir());

        listView = findViewById(R.id.my_list);

        mylist = new ArrayList<>();
        for (String file_name : handler.getFileNames()) {
            mylist.add(file_name);
        }


        addToList();


        // When clicks on one of the recipes names, you are redirected to that recipe view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 0; i <= mylist.size(); i++){
                        if(position == i) {
                            Intent intent = new Intent(view.getContext(), DisplayRecipeActivity.class);
                            Intent send = intent.putExtra("Name", recipeAdapter.getItem(i).getName());
                            startActivity(send);
                        }
                }
            }
        });
    }

    //  Instantly search while the user enters any character
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu );
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                recipeAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //    Put recipes inside of the list to be displayed for searching
    public void addToList() {
        ArrayList<Recipe> allRecipes = handler.getRecipes();
        recipeAdapter = new RecipeAdapter(allRecipes,this,android.R.layout.simple_list_item_1);
        listView.setAdapter(recipeAdapter);

    }


}
