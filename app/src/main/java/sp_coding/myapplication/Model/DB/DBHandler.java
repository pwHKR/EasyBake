package sp_coding.myapplication.Model.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.Interface.DataStorage;
import sp_coding.myapplication.Model.Object.Ingredient;
import sp_coding.myapplication.Model.Object.Link;
import sp_coding.myapplication.Model.Object.Recipe;
import sp_coding.myapplication.Model.System.Logic;

import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.BOOLEAN_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.CREATE_INGREDIENT_TABLE;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.KEY_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.NAME_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.TABLE_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Link.CREATE_LINK_TABLE;
import static sp_coding.myapplication.Model.DB.Table.Table_Link.KEY_LINK;
import static sp_coding.myapplication.Model.DB.Table.Table_Link.TABLE_LINK;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.CREATE_RECIPE_TABLE;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.F_KEY_LINK;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.INFO_RECIPE;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.KEY_ID_RECIPE;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.NAME_RECIPE;
import static sp_coding.myapplication.Model.DB.Table.Table_Recipe.TABLE_RECIPE;


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


    @Override
    public void onCreate(SQLiteDatabase db) {


        // Create Ingredient_Table


        db.execSQL(CREATE_INGREDIENT_TABLE);

        db.execSQL("ALTER TABLE ingredient ADD COLUMN inStock INTEGER DEFAULT 0");


        // Create Recipe_Table

        db.execSQL(CREATE_RECIPE_TABLE);

        db.execSQL("ALTER TABLE recipe ADD COLUMN infoText Text DEFAULT 0");
        db.execSQL("ALTER TABLE recipe ADD COLUMN idIngredient INTEGER DEFAULT 0");

        // Create Link_Table

        db.execSQL(CREATE_LINK_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINK);

        // Create tables again
        onCreate(db);

    }


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INGREDIENT, ingredient.getId()); // Ingredient ID
        values.put(NAME_INGREDIENT, ingredient.getName()); // Ingredient Name
        values.put(BOOLEAN_INGREDIENT, ingredient.getInStock_TinyInt()); // If Ingredient in stock


        // Inserting Row
        db.insert(TABLE_INGREDIENT, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
    public Ingredient getIngredient(int id) {


        Logic logic = new Logic();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INGREDIENT, new String[]{KEY_INGREDIENT,
                        NAME_INGREDIENT, BOOLEAN_INGREDIENT}, KEY_INGREDIENT + "=?",
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
        values.put(KEY_ID_RECIPE, recipe.getId()); // Recipe ID
        values.put(NAME_RECIPE, recipe.getName()); // Recipe Name
        values.put(INFO_RECIPE, recipe.getInfoText());
        values.put(F_KEY_LINK, recipe.getIdIngredient());


        // Inserting Row
        db.insert(TABLE_RECIPE, null, values);
        db.close(); // Closing database connection
    }

    public Recipe getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPE, new String[]{KEY_ID_RECIPE,
                        NAME_RECIPE,}, KEY_ID_RECIPE + "=?",
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

                // Adding Ingredient to list
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientList;
    }


    public void addLink(Link link) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LINK, link.getIdLink()); // Link ID
        values.put(F_KEY_LINK, link.getIdRecipe()); // Recipe Name

        for (int i = 0; i < link.getIngredientNum().length; i++) {


            if (link.getIngredientNum()[i] != 0) {

                values.put("num" + (i + 1), link.getIngredientNum()[i]);
            }

        }


        // Inserting Row
        db.insert(TABLE_LINK, null, values);
        db.close(); // Closing database connection
    }


    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<Recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "recipe";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

                // Adding Recipe to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        // return Recipe list
        return recipeList;
    }


    public List<Link> getAllLink() {

        int numArray[] = new int[30];
        int linkId = 0;
        int idRecipe = 0;


        List<Link> linkList = new ArrayList<Link>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "recipe";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //  Link link = new Link(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2),
                //    cursor.getInt(3));

                linkId = cursor.getInt(0);
                idRecipe = cursor.getInt(1);
                numArray[0] = cursor.getInt(2);
                numArray[1] = cursor.getInt(3);


                Link link = new Link(linkId, idRecipe, numArray);

                // Adding Link to list
                linkList.add(link);
            } while (cursor.moveToNext());
        }

        // return Link list
        return linkList;
    }
}
