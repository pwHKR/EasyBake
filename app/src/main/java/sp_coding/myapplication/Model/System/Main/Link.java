package sp_coding.myapplication.Model.System.Main;

import android.util.Log;

import sp_coding.myapplication.Model.System.Abstract.Id;

/**
 * Created by woojen on 2018-02-15.
 */

public class Link extends Id {

    private int IdRecipe; // id reference to a Recipe object

    private int[] listIngredient = new int[30];


    public int getIdRecipe() {
        return IdRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        IdRecipe = idRecipe;
    }

    public Link(int id, int idRecipe, int[] listIngredient) {
        super(id);
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

        Log.d("Link ID " + getId(), "-----------\n");


        for (int i : listIngredient) {


            if (i != 0)

                Log.d("Included", +i + "\n");


        }


    }


}

