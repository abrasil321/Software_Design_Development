package com.example.cs246teamproject_cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class CreateRecipeActivity extends AppCompatActivity {

    Button btnSave, btnNewTag, btnNewIngredient, btnCancel;
    EditText txtName, txtNewTag, txtDescription, txtNewIngredient, txtMeasurement, txtInstructions;
    private Recipe recipe;
    TextView txtTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        recipe = new Recipe();
        btnSave = findViewById(R.id.buttonSaveNewRecipe);
        btnSave.setOnClickListener((v)->saveRecipe());
        btnNewIngredient = findViewById(R.id.buttonNewIngredient);
        btnNewIngredient.setOnClickListener((v)->addIngredient());
        btnNewTag = findViewById(R.id.buttonNewTag);
        btnNewTag.setOnClickListener((v)->addTag());
        btnCancel = findViewById(R.id.buttonCancelCreation);
        btnCancel.setOnClickListener((v)->cancel());

        txtName = findViewById(R.id.txtName);
        txtNewTag = findViewById(R.id.txtNewTag);
        txtNewIngredient = findViewById(R.id.txtNewIngredient);
        txtMeasurement = findViewById(R.id.txtMeasurement);
        txtInstructions = findViewById(R.id.txtInstructions);
        txtDescription = findViewById(R.id.txtDescription);

        txtTags = findViewById(R.id.txtCurrentTags);
    }

    public void saveRecipe() {
        Log.d("CRA", "recipeFileHandler creating");
        recipeFileHandler handler = new recipeFileHandler(getApplicationContext().getFilesDir());
        String name = txtName.getText().toString();
        Log.d("CRA", "name is: " + name);
        recipe.setName(name);
        Log.d("CRA", "Passed name");
        String description = txtDescription.getText().toString();
        recipe.setDescription(description);
        Log.d("CRA", "Passed Description");
        String instructions = txtInstructions.getText().toString();
        recipe.setInstructions(instructions);
        Log.d("CRA", "Passed Instructions");
        Log.d("CRA", "Save recipe");
        if (handler.addRecipe(recipe))
            finish();
    }

    public void addIngredient(){
        String measurement = txtMeasurement.getText().toString();
        String ingredient = txtNewIngredient.getText().toString();
        txtMeasurement.setText("");
        txtNewIngredient.setText("");
        if (measurement.isEmpty())
            measurement = "N/A";
        recipe.addIngredient(ingredient,measurement);

    }

    public void addTag(){
        Log.i("CRA", "adding tag " + txtNewTag.getText().toString());
        String newTag = txtNewTag.getText().toString();
        recipe.addTag(newTag);
        txtNewTag.setText("");
        //txtTags.setText(txtTags.getText().toString() + ", " + newTag);
        String tagText = "";
        for (String tag:recipe.getTags()) {
            tagText += tag + ", ";
        }
        txtTags.setText("Tags: " + tagText);
    }

    private void cancel(){
        finish();
    }

}

