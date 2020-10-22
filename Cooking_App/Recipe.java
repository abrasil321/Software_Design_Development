package com.example.cs246teamproject_cookingapp;

import com.google.gson.Gson;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Recipe {
    //model
    private String name;
    private String description;
    private String instructions;
    private HashMap<String, String> ingredients;
    //dictionary ("Sugar","1/2 cup")
    //ingredients.get("Sugar") ---> "1/2 cup"
    //public String[] tags;
    private Set<String> tags;
    //Tasty, Favorite...
    private Boolean editable;

    Recipe() {
        editable = true;
        name = new String("No Name");
        description = new String("No Description");
        instructions = new String("No Instructions");
        ingredients = new HashMap<>();
        tags = new TreeSet<String>();
    }

    Recipe(String name) {
        editable = true;
        this.name = name;
        description = new String("No Description");
        instructions = new String("No Instructions");
        ingredients = new HashMap<>();
        tags = new TreeSet<String>();
    }

    public void setName(String name) {
        if (editable) this.name = name;
    }

    public void setDescription(String description) {
        if (editable) this.description = description;
    }

    public void setInstructions(String instructions) {
        if (editable) this.instructions = instructions;
    }

    public void addTag(final String newTag) {
        if (editable)
            tags.add(newTag);
    }

    public void addIngredient(String ingredient, String measurement) {
        if (!editable)
            return;
        if (!ingredients.containsKey(ingredient) && ingredients.get(ingredient) != null) {
            ///ingredients.get(ingredient) = ingredients.get(ingredient) + measurement;
            ingredients.put(ingredient, ingredients.get(ingredient) + " + " + measurement);
        } else
            ingredients.put(ingredient, measurement);
    }

    public void removeIngredient(String ingredient) {
        if (editable) ingredients.remove(ingredient);
    }

    //
    //

    /*
    
    */

    public void removeTag(String tag) {
        if (editable) tags.remove(tag);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String toJson() {
        Gson gson = new Gson();
        String recipeJson = gson.toJson(this);
        return recipeJson;
    }

    public void fromJson(String json) {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(json, Recipe.class);
        name = recipe.name;
        description = recipe.description;
        ingredients = recipe.ingredients;
        instructions = recipe.instructions;
        tags = recipe.tags;
        return;
    }

    public void setBasic(String text) {
        switch (text){
            case "pbj":
                setName("pbj");
                addIngredient("Peanutbutter","N\\A");
                addIngredient("Jelly","N\\A");
                addIngredient("Bread","2 slices");

                addTag("Sandwich");
                addTag("Quick");
                addTag("Easy");
                addTag("Cheap");
                addTag("Easy");
                setDescription("Simple sandwich that tastes good, a classic");
                setInstructions("You seriously don't know how to make this!!?");

        }
    }


}
