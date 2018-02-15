package sp_coding.myapplication.Model.Object;

/**
 * Created by woojen on 2018-02-15.
 */

public class Link {

    private int IdLink;
    private int IdRecipe;

    private String[] ingredientNum = new String[30];


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

    public String[] getIngredientNum() {
        return ingredientNum;
    }

    public void setIngredientNum(String[] ingredientNum) {
        this.ingredientNum = ingredientNum;
    }

    public Link(int idLink, int idRecipe, String[] ingredientNum) {
        IdLink = idLink;
        IdRecipe = idRecipe;
        this.ingredientNum = ingredientNum;
    }
}
