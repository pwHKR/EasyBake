package sp_coding.myapplication.Model.Utility.Ingredient;

import android.util.Log;

import java.util.List;

import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;

/**
 * Created by woojen on 2018-02-07.
 *
 * Use with Util Interface on fragments or activity's using DBHandler objects
 */

public class IngredientUtility extends Utility {


    public IngredientUtility() {

    }


    // Convert Int to Boolean

    public boolean convertTinyInt(int tinyInt) {


        boolean result;


        result = tinyInt != 0;

        return result;

    }

    // Convert boolean to Int

    public int convertBoolean(boolean input) {


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


    public void logIngredient(List<Ingredient> ingredients) {


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




}




