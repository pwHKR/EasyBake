package sp_coding.myapplication.Model;

/**
 * Created by woojen on 2018-02-07.
 */

public class Ingredient {

    private int id;
    private String name;
    private boolean inStock;
    private int inStock_TinyInt;


    public Ingredient(int id, String name, int inStock_TinyInt) {
        this.id = id;
        this.name = name;
        this.inStock_TinyInt = inStock_TinyInt;
    }

    public Ingredient(int id, String name, boolean inStock) {
        this.id = id;
        this.name = name;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
