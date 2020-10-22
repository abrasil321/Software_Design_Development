package com.example.cs246teamproject_cookingapp;

import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class recipeFileHandler {

    File mainDir;
    File recipeDir;
    static Dictionary<String,File> filesDict;

    recipeFileHandler(File mainDir){
        this.mainDir = mainDir;
        makeRecipeDirectory();
        fillFileTable();
    }

    private void fillFileTable(){
        filesDict = new Hashtable<String,File>();
        for (File file:getFiles()) {
            filesDict.put(file.getName(), file);
        }

    }

    public File findRecipe(String rName){
        return filesDict.get(rName);
    }

    public Recipe getRecipe(String name){
        Recipe recipe = new Recipe();
        File file = findRecipe(name);
        recipe.fromJson(readFromFile(file));
        return recipe;
    }

    public void makeRecipeDirectory(){
        recipeDir = new File(mainDir, "Recipes");
        Log.d("THIS ACTIVITY", "makeRecipeDirectory");
        boolean dirCreated = false;
        try {
            dirCreated = recipeDir.mkdir();
        } catch (Exception e) {
            Log.d("THIS ACTIVITY", "Error while creating directory" + e);
            e.printStackTrace();
        }
        if (dirCreated) {
            Log.d("THIS ACTIVITY", "Directory Created at " + recipeDir.getPath());
        }
        else{
            Log.d("THIS ACTIVITY", "Directory already exists");
        }
    }

    public File makeFile(String fileName){
        File file = new File(recipeDir, fileName );
        boolean fileCreated = false;
        try {
            fileCreated = file.createNewFile();

        } catch (IOException e) {
            Log.d("THIS ACTIVITY", "Error while creating file " + e);
            e.printStackTrace();
        }
        if (fileCreated) {
            Log.d("THIS ACTIVITY", "File Created at " + file.getPath());
        }
        else {
            Log.d("THIS ACTIVITY", "File already exists");
        }
        return file;
    }

    public boolean writeToFile(File file, String text){
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            Log.d("THIS ACTIVITY", "Writing " + text + " to " + file.getPath());
            fw.close();
            return true;
        } catch (IOException e) {
            Log.d("THIS ACTIVITY", "Error while writing file " + e);
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFile(File file){
        String data = "";
        try{
            Log.d("THIS ACTIVITY","reading file");
            Scanner reader = new Scanner(file);
            Log.d("THIS ACTIVITY","opened file");
            while(reader.hasNext())
                data += reader.nextLine();
            Log.d("THIS ACTIVITY","getting text");
            System.out.println(data);
            Log.d("THIS ACTIVITY","printed message");
            return data;
        }
        catch (IOException e) { e.printStackTrace(); }
        return data;
    }

    public File[] getFiles(){
        return recipeDir.listFiles();
    }

    public ArrayList<Recipe> getRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (File f : getFiles()) {//open all files and get the recipes
            Recipe r = new Recipe();
            r.fromJson(readFromFile(f));
            recipes.add(r);
        }
        return recipes;
    }

    public boolean addRecipe(Recipe recipe){
        File recipeFile = makeFile(recipe.getName());
        if (writeToFile(recipeFile, recipe.toJson()))
            return true;
        return false;
    }

    public static ArrayList<String> getFileNames(){
        return Collections.list(filesDict.keys());
    }
}

