package sp_coding.myapplication.Model;

/**
 * Created by woojen on 2018-02-07.
 */

public class Recipe {

    private int id;
    private String name;
    private String infoText;
    private Ingredient[] ingredients;

    private enum difficulty {LOW, MEDIUM, HARD}

    public Recipe(int id, String name, String infoText, Ingredient[] ingredients) {
        this.id = id;
        this.name = name;
        this.infoText = infoText;
        this.ingredients = ingredients;

        ingredients = new Ingredient[30];
    }

    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }


    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infoText='" + infoText + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
