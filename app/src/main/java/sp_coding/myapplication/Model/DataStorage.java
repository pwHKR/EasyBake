package sp_coding.myapplication.Model;

import java.util.List;

/**
 * Created by woojen on 2018-02-08.
 */

public interface DataStorage {

    // Ingredient storing

    // Set
    void addIngredient(Ingredient ingredient);

    // Get

    Ingredient getIngredient(int id);

    // Print

    List<Ingredient> getAllIngredients();



    // Recipe storing


    // Set
    void addRecipe(Recipe recipe);


    // Get
    Recipe getRecipe(int id);


}
