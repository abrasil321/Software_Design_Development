package com.example.cs246teamproject_cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button buttonViewRecipe,buttonCreateRecipe;
    public Button buttonSearchingTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCreateRecipe = findViewById(R.id.buttonCreateRecipe);
        buttonCreateRecipe.setOnClickListener((v)->openCreateRecipe());
        buttonSearchingTool = findViewById(R.id.buttonSearchingTool);
        buttonSearchingTool.setOnClickListener((v)->openSearchingTool());
    }

    public void openViewRecipe()
    {
        Intent intent = new Intent(this, DisplayRecipeActivity.class);
        startActivity(intent);
    }

    public void openCreateRecipe() {
        Intent intent = new Intent(this, CreateRecipeActivity.class);
        startActivity(intent);
    }

    public void openSearchingTool() {
        Intent intent = new Intent(MainActivity.this, SearchingTool.class);
        startActivity(intent);
    }
}
