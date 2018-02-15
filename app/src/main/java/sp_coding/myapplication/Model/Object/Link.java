package sp_coding.myapplication.Model.Object;

/**
 * Created by woojen on 2018-02-15.
 */

public class Link {

    private String IdLink;
    private String IdRecipe;

    private String[] ingredientNum = new String[]{


            "num1",
            "num2",
            "num3",
            "num4",
            "num5",
            "num6",
            "num7",
            "num8",
            "num9",
            "num10;",
            "num11",
            "num12",
            "num13",
            "num14",
            "num15",
            "num16",
            "num17",
            "num18",
            "num19",
            "num20",
            "num21",
            "num22",
            "num23",
            "num24",
            "num25",
            "num26",
            "num27",
            "num28",
            "num29",
            "num30"};


    public String getIdLink() {
        return IdLink;
    }

    public void setIdLink(String idLink) {
        IdLink = idLink;
    }

    public String getIdRecipe() {
        return IdRecipe;
    }

    public void setIdRecipe(String idRecipe) {
        IdRecipe = idRecipe;
    }

    public String[] getIngredientNum() {
        return ingredientNum;
    }

    public void setIngredientNum(String[] ingredientNum) {
        this.ingredientNum = ingredientNum;
    }
}
