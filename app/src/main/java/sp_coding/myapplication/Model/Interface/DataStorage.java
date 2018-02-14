package sp_coding.myapplication.Model.Interface;

import java.util.List;

import sp_coding.myapplication.Model.Object.Ingredient;
import sp_coding.myapplication.Model.Object.Recipe;

/**
 * Created by woojen on 2018-02-08.
 */

// Implement this interface when creating a storage class for this project

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
