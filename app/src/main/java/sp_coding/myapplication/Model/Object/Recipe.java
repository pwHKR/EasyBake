package sp_coding.myapplication.Model.Object;

/**
 * Created by woojen on 2018-02-07.
 */

public class Recipe {

    private int id;
    private String name;
    private String infoText;
    private int idIngredient; // Tänkte att vi skapar en tabbel enbart för ingridenser i ett recpet och detta Id för vara forign key elr nått.


    public Recipe(int id, String name, String infoText) {
        this.id = id;
        this.name = name;
        this.infoText = infoText;


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


    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infoText='" + infoText + '\'' +
                ", idIngredient=" + idIngredient +
                '}';
    }
}
