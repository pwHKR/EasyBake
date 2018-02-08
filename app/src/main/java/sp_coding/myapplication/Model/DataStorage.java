package sp_coding.myapplication.Model;

/**
 * Created by woojen on 2018-02-08.
 */

public interface DataStorage {

    // Ingredient storing
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    // Recipe storing
    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
