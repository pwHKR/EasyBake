package sp_coding.myapplication.Model.Utility.Ingredient;

import android.util.Log;

import java.util.List;

import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;

/**
 * Created by woojen on 2018-02-07.
 * <p>
 * Use with Util Interface on fragments or activity's
 */

public class IngredientUtility extends Utility {
    public IngredientUtility() {
    }


// Convert Int to Boolean

    public static boolean convertTinyInt(int tinyInt) {


        boolean result;


        result = tinyInt != 0;

        return result;

    }

    // Convert boolean to Int

    public static int convertBoolean(boolean input) {


        int result = 0;

        String StringValue = String.valueOf(input);


        if (StringValue.equalsIgnoreCase("false")) {

            result = 0;
        }

        if (StringValue.equalsIgnoreCase("true")) {

            result = 1;
        }

        return result;

    }


    public void NewIngredient(String name, boolean inStock) {

        int newId;

        int inStockTinyInt = convertBoolean(inStock);

        Log.d("FCT", String.valueOf(inStockTinyInt));

        newId = getNewID("ingredient");

        Ingredient ingredient = new Ingredient(newId, name, inStockTinyInt);


        dbh.addIngredient(ingredient);
    }


    public void logIngredient() {

        List<Ingredient> ingredients = dbh.getAllIngredients();

        int loopCount = 0;

        for (Ingredient i : ingredients) {

            loopCount++;

            Log.d("Element " + String.valueOf(loopCount) + ": ",
                    "name: " + i.getName() + "\n" + "id: " + i.getId() + "\n" + "in stock: "
                            + i.isInStock());
        }

    }

    public List<Ingredient> getCompleteList() {

        return dbh.getAllIngredients();
    }

    public List<String> getCompleteNameList() {
        return dbh.getAllIngredientNames();
    }


    public boolean delete(String ingredientName) {

        boolean inRecipe;

        inRecipe = dbh.deleteIngredient(dbh.getIngredientId(ingredientName));

        return inRecipe;

    }

    public void setinStock(String ingredientName, boolean isInStock) {

        dbh.setIngredientInStock(dbh.getIngredientId(ingredientName), isInStock);


    }

    public int getId(String name) {

        int id = dbh.getIngredientId(name);

        return id;
    }


    // This method will return the number of recipes that contains a certain ingredient
    public int xInRecipe(int ingredientID) {

        List<Recipe> recipeList;
        Link link;
        int[] ingredientList;


        int inRecipeAmount = 0;

        recipeList = dbh.getAllRecipes();

        for (Recipe recipe : recipeList) {

            link = dbh.getLinkIngredient(recipe.getId());

            ingredientList = link.getListIngredient();


            for (int i : ingredientList) {

                if (i == ingredientID) {

                    inRecipeAmount++;
                }

            }


        }


        if (inRecipeAmount == 0) {

            dbh.setIngredient_InRecipe(ingredientID, false);

        }

        return inRecipeAmount;
    }


}




