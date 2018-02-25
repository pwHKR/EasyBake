package sp_coding.myapplication.Model.Interface;

import java.util.List;

import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;

/**
 * Created by woojen on 2018-02-08.
 */

// Implement this interface when creating or changing the storage class for this project

public interface DataStorage {

    // Ingredient storing

    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    List<Ingredient> getAllIngredients();



    // Recipe storing

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    List<Recipe> getAllRecipes();


    // Link storing

    void addLink(Link link);

    List<Link> getAllLink();

    // Storage Utility methods

    String getIngredientName(int id);

    int getCount(String table);

}
