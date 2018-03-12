package sp_coding.myapplication.Model.System.Main;

import sp_coding.myapplication.Model.System.Abstract.Id_Name;

/**
 * Created by woojen on 2018-02-07.
 */

public class Ingredient extends Id_Name {


    private boolean inStock;
    private int inStock_TinyInt;

    public boolean isInRecipe() {
        return InRecipe;
    }

    public void setInRecipe(boolean inRecipe) {
        InRecipe = inRecipe;
    }

    private boolean InRecipe;

    public Ingredient(int id, String name, boolean inStock) {

        super(id, name);

        this.inStock = inStock;
    }

    public Ingredient(int id, String name, int inStock_TinyInt) {
        super(id, name);

        this.inStock_TinyInt = inStock_TinyInt;
    }

    public Ingredient(int id, String name, boolean inStock, boolean inRecipe) {
        super(id, name);
        this.inStock = inStock;
        InRecipe = inRecipe;
    }



    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }


    public int getInStock_TinyInt() {
        return inStock_TinyInt;
    }

    public void setInStock_TinyInt(int inStock_TinyInt) {
        this.inStock_TinyInt = inStock_TinyInt;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + super.id +
                ", name='" + name + '\'' +
                ", inStock=" + inStock +
                '}';
    }


}
