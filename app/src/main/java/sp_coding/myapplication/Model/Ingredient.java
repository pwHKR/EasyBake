package sp_coding.myapplication.Model;

/**
 * Created by woojen on 2018-02-07.
 */

public class Ingredient {

    private int id;
    private String name;
    private boolean inStock;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(String name, boolean inStock) {
        this.name = name;
        this.inStock = inStock;
    }

    public Ingredient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(int id, String name, boolean inStock) {
        this.id = id;
        this.name = name;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inStock=" + inStock +
                '}';
    }
}
