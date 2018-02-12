package sp_coding.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woojen on 2018-02-07.
 */

public class DBHandler extends SQLiteOpenHelper implements DataStorage {

    Logic logic = new Logic();


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bakeManager";


    // Ingredient table name
    private static final String TABLE_INGREDIENT = "ingredient";

    // Recipe table name
    private static final String TABLE_RECIPE = "recipe";


    // Ingredient Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_INSTOCK = "inStock";


    // Recipe Table Columns names
    private static final String KEY_ID2 = "id";
    private static final String KEY_NAME2 = "name";

    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_Ingredient_TABLE = "CREATE TABLE " + TABLE_INGREDIENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";

        db.execSQL(CREATE_Ingredient_TABLE);

        db.execSQL("ALTER TABLE ingredient ADD COLUMN inStock INTEGER DEFAULT 0");


        String CREATE_Recipe_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_NAME2 + " TEXT" + ")";

        db.execSQL("ALTER TABLE recipe ADD COLUMN infoText Text DEFAULT 0");
        db.execSQL("ALTER TABLE recipe ADD COLUMN idIngredient INTEGER DEFAULT 0");


        db.execSQL(CREATE_Recipe_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);

        // Create tables again
        onCreate(db);

    }


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, ingredient.getId()); // Ingredient ID
        values.put(KEY_NAME, ingredient.getName()); // Ingredient Name
        values.put(KEY_INSTOCK, ingredient.getInStock_TinyInt()); // If Ingredient in stock


        // Inserting Row
        db.insert(TABLE_INGREDIENT, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
    public Ingredient getIngredient(int id) {


        Logic logic = new Logic();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INGREDIENT, new String[]{KEY_ID,
                        KEY_NAME, KEY_INSTOCK}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ingredient ingredient = new Ingredient(cursor.getInt(0), cursor.getString(1), logic.convertTinyInt(cursor.getInt(2)));
        // return Ingredient
        return ingredient;
    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID2, recipe.getId()); // Recipe ID
        values.put(KEY_NAME2, recipe.getName()); // Recipe Name
        values.put("infoText", recipe.getInfoText());
        values.put("idInteger", recipe.getIdIngredient());


        // Inserting Row
        db.insert(TABLE_RECIPE, null, values);
        db.close(); // Closing database connection
    }

    public Recipe getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPE, new String[]{KEY_ID2,
                        KEY_NAME2,}, KEY_ID2 + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1));
        // return recipe
        return recipe;
    }

    public int getCount(String table) {

        int countResult;

        String countQuery = "SELECT  * FROM " + table + "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        countResult = cursor.getCount();
        cursor.close();

        // return count
        return countResult;

    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "ingredient";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient ingredient = new Ingredient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), logic.convertTinyInt(cursor.getInt(2)));

                /*

                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setName(cursor.getString(1));

                int inStock = cursor.getInt(2);
                boolean stock = logic.convertTinyInt(inStock);

                Log.d("boolean Stock:", String.valueOf(stock));
                ingredient.setInStock(stock);

                */
                // Adding Ingredient to list
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientList;
    }
}
