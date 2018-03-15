package sp_coding.myapplication.Model.Utility.Recipe;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;

/**
 * Created by woojen on 2018-02-26.
 *
 *
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


                dbh.setIngredient_InRecipe(id);

            }

        }

        Recipe recipe = new Recipe(getNewID("recipe"), inputName.getText().toString(), inputInfo.getText().toString());


        Link link = new Link(getNewID("link"), recipe.getId(), numArray);





        dbh.addLink(link);


        recipe.setIdIngredient(dbh.getMaxLinkId());

        dbh.addRecipe(recipe);


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

    public void tempTest() {

        IngredientUtility ingredientUtility = new IngredientUtility();

        dbh.setIngredientInStock(4, false);

        ingredientUtility.logIngredient();

    }

    public void tempTest2() {

        RecipeUtility recipeUtility = new RecipeUtility();
        LinkUtility linkUtility = new LinkUtility();

        dbh.deleteRecipeAndLink(2);

        logRecipe();



    }

    public String checkRecipeName(String name) {
        return dbh.checkIfRecipeNameExists(name);
    }

    public String getRecipeInfoText(String name) {
        return dbh.getRecipeInfo(name);
    }


}


