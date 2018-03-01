package sp_coding.myapplication.Model.Utility.Recipe;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;

/**
 * Created by woojen on 2018-02-26.
 *
 *
 * Use with Util Interface on fragments or activity's
 */

public class RecipeUtility extends Utility {


    public void newRecipe(ArrayList<String> ingredientList, EditText inputName,
                          EditText inputInfo) {

        int numArray[] = new int[30];


        for (int i = 0; i < 30; i++) {

            if (!ingredientList.get(i).toString().equalsIgnoreCase("")) {

                numArray[i] = Integer.parseInt(ingredientList.get(i).toString());
            }

        }

        Recipe recipe = new Recipe(getNewID("recipe"), inputName.getText().toString(), inputInfo.getText().toString());

        Link link = new Link(getNewID("link"), recipe.getId(), numArray);


        recipe.setIdIngredient(link.getId());


        dbh.addLink(link);

        dbh.addRecipe(recipe);


    }


    public void logRecipe() {

        List<Recipe> recipe = dbh.getAllRecipes();
        int loopCount = 0;

        for (Recipe i : recipe) {

            loopCount++;

            Log.d("RecipeElement " + String.valueOf(loopCount) + ": ",
                    "name " + i.getName() + "\n" + "Info text: " + i.getInfoText() + "\n" + "id: " + i.getId() + "\n" + "IdLink: "
                            + i.getIdIngredient());
        }
    }


}


