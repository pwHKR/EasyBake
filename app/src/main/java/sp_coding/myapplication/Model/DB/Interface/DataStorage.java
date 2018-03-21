package sp_coding.myapplication.Model.DB.Interface;

import java.util.List;

import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;

/**
 * Created by woojen on 2018-02-08.
 */

// Implement this interface when creating or changing the storage class for this project

public interface DataStorage {

    String someThing = "null";

    // Ingredient storing

    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    List<Ingredient> getAllIngredients();

    boolean deleteIngredient(int id);

    void setIngredient_InRecipe(int id, boolean flag);

    void setIngredientInStock(int id, boolean value);

    int getIngredientId(String name);


    // Recipe storing

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    List<Recipe> getAllRecipes();

    int getRecipeId(String name);

    List<String> getAllRecipeNames();

    String checkIfRecipeNameExists(String name);

    String getRecipeInfo(String name);

    int getMaxRecipeId();


    // Link storing

    void addLink(Link link);

    List<Link> getAllLink();

    Link getLinkIngredient(int recipeID);


    // Link and Recipe Storing

    void deleteRecipeAndLink(int recipeId);


    // Storage Utility methods

    String getIngredientName(int id);

    int getCount(String table);

}
