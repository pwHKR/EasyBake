package sp_coding.myapplication.Model.Utility.Recipe;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;

/**
 * Created by woojen on 2018-02-26.
 * <p>
 * <p>
 * Use with Util Interface on fragments or activity's
 */

public class RecipeUtility extends Utility {


    public void newRecipe(ArrayList<String> ingredientNameList, EditText inputName,
                          EditText inputInfo) {


        int numArray[] = new int[30];


        for (int i = 0; i < 30; i++) {


            if (!ingredientNameList.get(i).equalsIgnoreCase("")) {

                int id = dbh.getIngredientId(ingredientNameList.get(i));


                numArray[i] = id;


                dbh.setIngredient_InRecipe(id, true);

            }

        }

        Recipe recipe = new Recipe(getNewID("recipe"), inputName.getText().toString(), inputInfo.getText().toString());

        dbh.addRecipe(recipe);

        Link link = new Link(getNewID("link"), dbh.getMaxRecipeId(), numArray);


        dbh.addLink(link);


    }


    public void logRecipe() {

        List<Recipe> recipe = dbh.getAllRecipes();
        int loopCount = 0;

        for (Recipe i : recipe) {

            loopCount++;

            Log.d("RecipeElement " + String.valueOf(loopCount) + ": ",
                    "name " + i.getName() + "\n" + "Info text: " + i.getInfoText() + "\n" + "id: " + i.getId() + "\n"
            );
        }
    }

    public List<String> getCompleteNameList() {
        return dbh.getAllRecipeNames();
    }

    public ArrayList<Recipe> getRecipesWithInStockArg(boolean returnInStock) {


        List<Recipe> recipe = dbh.getAllRecipes();

        ArrayList<Recipe> recipeInStock = new ArrayList<>();
        ArrayList<Recipe> recipeNotInStock = new ArrayList<>();

        int[] ingredientList;

        boolean inStock = true;


        for (Recipe i : recipe) {

            Link link;

            link = dbh.getLinkIngredient(i.getId());

            ingredientList = link.getListIngredient();

            for (int p = 0; p < 30; p++) {

                if (ingredientList[p] != 0)

                    if (!dbh.getIngredient(ingredientList[p]).isInStock())

                        inStock = false;


            }


            if (inStock) {
                recipeInStock.add(i);
            } else {
                recipeNotInStock.add(i);
            }

        }

        if (returnInStock) {
            return recipeInStock;
        } else {
            return recipeNotInStock;
        }
    }



    public void delete(int recipeID) {

        RecipeUtility recipeUtility = new RecipeUtility();
        LinkUtility linkUtility = new LinkUtility();

        dbh.deleteRecipeAndLink(recipeID);

        logRecipe();


    }

    public String checkRecipeName(String name) {
        return dbh.checkIfRecipeNameExists(name);
    }

    public String getRecipeInfoText(String name) {
        return dbh.getRecipeInfo(name);
    }

    public int getRecipeID(String name) {
        return dbh.getRecipeId(name);
    }


    // Return true if you want in stock list and return false if you want not in stock
    public List<String> isInStockList(boolean inStockList) {

        List<Recipe> temp;
        List<String> recipeName;
        recipeName = new ArrayList<>();

        if (inStockList) {

            temp = getRecipesWithInStockArg(inStockList);


            for (Recipe r : temp) {

                recipeName.add(r.getName());

            }


        }

        return recipeName;

    }



}


