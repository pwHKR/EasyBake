package sp_coding.myapplication.Model.System.Main;

import sp_coding.myapplication.Model.System.Abstract.Id_Name;

/**
 * Created by woojen on 2018-02-07.
 */

public class Recipe extends Id_Name {


    private String infoText;
    private int iD_Link;


    public Recipe(int id, String name, String infoText) {
        super(id, name);

        this.infoText = infoText;
    }

    public Recipe(int id, String name, String infoText, int iD_Link) {
        super(id, name);


        this.infoText = infoText;
        this.iD_Link = iD_Link;
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


    public int getiD_Link() {
        return iD_Link;
    }

    public void setiD_Link(int iD_Link) {
        this.iD_Link = iD_Link;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infoText='" + infoText + '\'' +
                ", iD_Link=" + iD_Link +
                '}';
    }
}
