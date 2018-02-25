package sp_coding.myapplication.Model.Object;

import android.util.Log;

/**
 * Created by woojen on 2018-02-15.
 */

public class Link {

    private int IdLink; // Reference to itself
    private int IdRecipe; // id reference to a Recipe object

    private int[] listIngredient = new int[30];


    public int getIdLink() {
        return IdLink;
    }

    public void setIdLink(int idLink) {
        IdLink = idLink;
    }

    public int getIdRecipe() {
        return IdRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        IdRecipe = idRecipe;
    }

    public Link(int idLink, int idRecipe, int[] listIngredient) {
        IdLink = idLink;
        IdRecipe = idRecipe;
        this.listIngredient = listIngredient;
    }

    public int[] getListIngredient() {
        return listIngredient;
    }

    public void setListIngredient(int[] listIngredient) {
        this.listIngredient = listIngredient;
    }

    public void ListIterator() {

        Log.d("Link ID " + getIdLink(), "-----------\n");


        for (int i : listIngredient) {


            if (i != 0)

                Log.d("Included", +i + "\n");


        }


    }


}

