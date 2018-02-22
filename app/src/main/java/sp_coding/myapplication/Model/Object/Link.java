package sp_coding.myapplication.Model.Object;

/**
 * Created by woojen on 2018-02-15.
 */

public class Link {

    private int IdLink; // Reference to itself
    private int IdRecipe; // id reference to a Recipe object

    private int[] ingredientNum = new int[30];


    public int getIdLink() {
        return IdLink;
    }

    public void setIdLink(int idLink) {
        IdLink = idLink;
    }

    public int getIdRecipe() {
        return IdRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        IdRecipe = idRecipe;
    }

    public Link(int idLink, int idRecipe, int[] ingredientNum) {
        IdLink = idLink;
        IdRecipe = idRecipe;
        this.ingredientNum = ingredientNum;
    }

    public int[] getIngredientNum() {
        return ingredientNum;
    }

    public void setIngredientNum(int[] ingredientNum) {
        this.ingredientNum = ingredientNum;
    }
}
