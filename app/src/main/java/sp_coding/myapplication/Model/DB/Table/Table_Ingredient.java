package sp_coding.myapplication.Model.DB.Table;

import sp_coding.myapplication.Model.Interface.DB.Table;

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

    // Create statement

    public static final String CREATE_INGREDIENT_TABLE = "CREATE TABLE " + TABLE_INGREDIENT + "("
            + KEY_INGREDIENT + " INTEGER PRIMARY KEY," + NAME_INGREDIENT + " TEXT,"
            + BOOLEAN_INGREDIENT + " INTEGER"
            + ")";


    @Override
    public String printTable() {

        String output = "Table: " + TABLE_INGREDIENT + "\n" + "-----------" +
                KEY_INGREDIENT + "\n" + NAME_INGREDIENT + "\n" + BOOLEAN_INGREDIENT;

        return output;
    }
}
