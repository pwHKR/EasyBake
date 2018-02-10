package sp_coding.myapplication.Model;

import java.util.List;

/**
 * Created by woojen on 2018-02-08.
 */

public interface DataStorage {

    // Ingredient storing
    void addIngredient(Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredient(int id);

    // Recipe storing
    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    // General Storage

    int getCount(String table); // Get count from table (in DB case)


}
