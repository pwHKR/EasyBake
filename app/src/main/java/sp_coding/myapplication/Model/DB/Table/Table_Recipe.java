package sp_coding.myapplication.Model.DB.Table;


import sp_coding.myapplication.Model.DB.Interface.Table;

/**
 * Created by woojen on 2018-02-14.
 */

public class Table_Recipe implements Table {

    // Recipe table name
    public static final String TABLE_RECIPE = "recipe";


    // Recipe table columns names
    public static final String KEY_ID_RECIPE = "id";
    public static final String NAME_RECIPE = "name";
    public static final String INFO_RECIPE = "infoText";
    public static final String F_KEY_LINK = "IdIngredient";


    public static final String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
            + KEY_ID_RECIPE + " INTEGER PRIMARY KEY," + NAME_RECIPE + " TEXT,"
            + INFO_RECIPE + " Text," + F_KEY_LINK + " Integer" +
            ")";


    @Override
    public String printTable() {
        String output = "Table: " + TABLE_RECIPE + "\n" + "-----------" +
                KEY_ID_RECIPE + "\n" + NAME_RECIPE + "\n" + INFO_RECIPE +
                "\n" + F_KEY_LINK;

        return output;
    }
}
