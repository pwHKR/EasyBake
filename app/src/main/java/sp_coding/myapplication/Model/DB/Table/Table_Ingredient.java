package sp_coding.myapplication.Model.DB.Table;

import sp_coding.myapplication.Model.DB.Interface.Table;

/**
 * Created by woojen on 2018-02-14.
 */

public class Table_Ingredient implements Table {

    // Ingredient table name
    public static final String TABLE_INGREDIENT = "ingredient";


    // Ingredient table columns names
    public static final String KEY_INGREDIENT = "id";
    public static final String NAME_INGREDIENT = "name";
    public static final String BOOLEAN_INGREDIENT = "inStock";
    public static final String BOOLEAN_INRECIPE = "inRecipe";

    // Create statement

    public static final String CREATE_INGREDIENT_TABLE = "CREATE TABLE " + TABLE_INGREDIENT + "("
            + KEY_INGREDIENT + " INTEGER PRIMARY KEY," + NAME_INGREDIENT + " TEXT,"
            + BOOLEAN_INGREDIENT + " INTEGER," + BOOLEAN_INRECIPE + " INTEGER"
            + ")";


    // Default Ingredients

    private static final String D1 = "INSERT INTO ingredient VALUES (1,'Sugar',0,0);";
    private static final String D2 = "INSERT INTO ingredient VALUES (2,'Saffron',0,0);";
    private static final String D3 = "INSERT INTO ingredient VALUES (3,'Salt',0,0);";
    private static final String D4 = "INSERT INTO ingredient VALUES (4,'Baking Powder',0,0);";
    private static final String D5 = "INSERT INTO ingredient VALUES (5,'Vanilla',0,0);";
    private static final String D6 = "INSERT INTO ingredient VALUES (6,'Flour',0,0);";


    // Default Ingredient Array

    public static final String[] DEFAULT_INGREDIENT = {D1, D2, D3, D4, D5, D6};










    @Override
    public String printTable() {

        String output = "Table: " + TABLE_INGREDIENT + "\n" + "-----------" +
                KEY_INGREDIENT + "\n" + NAME_INGREDIENT + "\n" + BOOLEAN_INGREDIENT + "\n" + BOOLEAN_INRECIPE;

        return output;
    }
}
