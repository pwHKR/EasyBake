package sp_coding.myapplication.Model.System.Main;

import sp_coding.myapplication.Model.System.Abstract.Id_Name;

/**
 * Created by woojen on 2018-02-07.
 */

public class Recipe extends Id_Name {


    private String infoText;
    private int idIngredient;


    public Recipe(int id, String name, String infoText) {
        super(id, name);

        this.infoText = infoText;
    }

    public Recipe(int id, String name, String infoText, int idIngredient) {
        super(id, name);


        this.infoText = infoText;
        this.idIngredient = idIngredient;
    }

    public Recipe(int id, String name) {
        super(id, name);


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
