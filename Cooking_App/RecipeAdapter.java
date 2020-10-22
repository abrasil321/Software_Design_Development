package com.example.cs246teamproject_cookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends BaseAdapter implements Filterable {
    private List<Recipe> recipes;
    private List<Recipe> recipesFiltered;
    private Context context;
    private int resourceLayout;


    public RecipeAdapter(List<Recipe> recipes, Context context, int resourceLayout) {
        this.recipes = recipes;
        this.recipesFiltered = recipes;
        this.context = context;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return recipesFiltered.size();
    }

    @Override
    public Recipe getItem(int position) {
        return recipesFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView txt;

        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resourceLayout,parent,false);
        }

        txt = (TextView) v;

        Recipe r = getItem(position);
        txt.setText(r.getName());
        //if (r != null){

        //}
        //TextView tt1 = (TextView) v.findViewById()
        return v;
}

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0)
                {
                    filterResults.count = recipes.size();
                    filterResults.values = recipes;
                }
                else {
                    List<Recipe> resultsRecipes = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Recipe r:recipes){
                        if (r.getName().toLowerCase().contains(searchStr))
                        {
                            resultsRecipes.add(r);
                        } else
                        {
                            for(String s : r.getTags()){
                                if (s.toLowerCase().contains(searchStr)) {
                                    resultsRecipes.add(r);
                                    break;
                                }
                            }
                        }
                    }
                    filterResults.count = resultsRecipes.size();
                    filterResults.values = resultsRecipes;
                    recipesFiltered = resultsRecipes;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                recipesFiltered = (List<Recipe>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
