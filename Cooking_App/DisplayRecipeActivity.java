package com.example.cs246teamproject_cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;
import java.util.logging.FileHandler;

public class DisplayRecipeActivity extends AppCompatActivity {

    public Button buttonTimer, buttonChart;
    public TextView textRecipeName, textIngredients, textInstructions, textTagView, textDescription;
    public Recipe recipe;

    //public recipe;
//pass only the string name of the recipe into this activity then call the filehandler to open the file and get its contents
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);
        Log.d("DRA","Starting to display recipe");

        textRecipeName = findViewById(R.id.textRecipeName);
        textIngredients = findViewById(R.id.textIngredients);
        textInstructions = findViewById(R.id.textInstructions);
        textTagView = findViewById(R.id.txtTagsView);
        textDescription = findViewById(R.id.textDescriptionDR);
        Log.d("DRA","Layout successfully implemented");


        String fileName = getIntent().getStringExtra("Name");
        Log.d("DRA","Get intent");
        if (fileName != null){
            Log.d("DRA","'name' in intent");
            recipeFileHandler handler = new recipeFileHandler(getApplicationContext().getFilesDir());
            recipe = handler.getRecipe(fileName);
            Log.d("DRA","Successfully loaded recipe");
        }
        else {
            Log.d("DRA","No 'name ' in intent");
            recipe = new Recipe();
            Log.d("DRA","Successfully created blank recipe");
        }
        setRecipeName();

        setRecipeIngredients();
        setRecipeInstructions();

        setRecipeDescription();
        setRecipeTags();
        Log.d("DRA","Successfully set all recipe data");

        buttonTimer = findViewById(R.id.buttonTimer);
        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimer();
            }
        });

        buttonChart = findViewById(R.id.buttonChart);
        buttonChart.setOnClickListener((v)-> Chart());
    }

    public void setRecipeName()
    {
        Log.d("DRA","Setting recipe name");
        textRecipeName.setText(recipe.getName());
    }
    public void setRecipeIngredients()
    {
        Log.d("DRA","Setting recipe ingredients");
        String text = "Ingredients: \n";
        for (Map.Entry<String,String> ingredient:recipe.getIngredients().entrySet()) {
            text += ingredient.getKey();
            if (!ingredient.getValue().equals("n\\a")){
             text += " : " + ingredient.getValue();
            }
            text += "\n";
        }
        //String text = "Ingredients:\n1.\n2.\n3.\n\n";
        textIngredients.setText(text);
    }
    public void setRecipeInstructions()
    {
        Log.d("DRA","Setting recipe instructions");
        String text = "Instructions:\n";
        text += recipe.getInstructions();
        textInstructions.setText(text);
    }

    public void openTimer()
    {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }

    public void Chart()
    {
        Intent intent = new Intent(this, chart.class);
        startActivity(intent);
    }

    private void setRecipeDescription(){
        Log.d("DRA","Setting recipe description");
        String text = "Description: " + recipe.getDescription();
//        if (!text.isEmpty())
//            text = "\n"+text;
        textDescription.setText(text);
    }
    private void setRecipeTags(){
        Log.d("DRA","Setting recipe tags");
        String text = "Tags: ";
        boolean first = true;
        int lineSize = 1;
        for (String tag:recipe.getTags()) {
            if (first)
                first = false;
            else
                text += ", ";
            text += tag;

            if (text.length() > lineSize * 200) {
                text += '\n';
                lineSize ++;
            }
        }
        textTagView.setText(text);
    }

}