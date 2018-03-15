package sp_coding.myapplication.Model.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.DB.Interface.DataStorage;
import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.System.Main.Recipe;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;

import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.BOOLEAN_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.CREATE_INGREDIENT_TABLE;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.DEFAULT_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.NAME_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Ingredient.TABLE_INGREDIENT;
import static sp_coding.myapplication.Model.DB.Table.Table_Link.CREATE_LINK_TABLE;
import static sp_coding.myapplication.Model.DB.Table.Table_Link.F_KEY_RECIPE;
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


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bakeManager";

    private static DBHandler sInstance;

    public static synchronized DBHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.

        if (sInstance == null) {
            sInstance = new DBHandler(context.getApplicationContext());
        }
        return sInstance;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {


        // Create Ingredient_Table


        db.execSQL(CREATE_INGREDIENT_TABLE);


        // Create Recipe_Table

        db.execSQL(CREATE_RECIPE_TABLE);


        // Create Link_Table

        db.execSQL(CREATE_LINK_TABLE);

        // Insert Default Ingredients

        for (String defaultIngredient : DEFAULT_INGREDIENT) {

            db.execSQL(defaultIngredient);
        }







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


    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Ingredient table methods


    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_INGREDIENT, ingredient.getId()); // Ingredient ID
        values.put(NAME_INGREDIENT, ingredient.getName()); // Ingredient Name
        values.put(BOOLEAN_INGREDIENT, ingredient.getInStock_TinyInt()); // If Ingredient in stock



        // Inserting Row
        db.insert(TABLE_INGREDIENT, null, values);
        db.close(); // Closing database connection
    }


    public Ingredient getIngredient(int id) {

        Ingredient ingredient = null;


        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + "ingredient WHERE id =" + String.valueOf(id);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                ingredient = new Ingredient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), IngredientUtility.convertTinyInt(cursor.getInt(2))
                        , IngredientUtility.convertTinyInt(cursor.getInt(3)));

            } while (cursor.moveToNext());
        }





        // return Ingredient
        cursor.close();
        return ingredient;
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
                Ingredient ingredient = new Ingredient(Integer.parseInt(cursor.getString(0)), cursor.getString(1), IngredientUtility.convertTinyInt(cursor.getInt(2))
                        , IngredientUtility.convertTinyInt(cursor.getInt(3)));

                // Adding Ingredient to list
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return ingredientList;
    }


    public List<String> getAllIngredientNames() {
        List<String> ingredientList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT name FROM " + "ingredient";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String ingredientName = cursor.getString(0);

                // Adding Ingredient to list
                ingredientList.add(ingredientName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list
        return ingredientList;
    }

    // Recipe table methods

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID_RECIPE, recipe.getId()); // Recipe ID
        values.put(NAME_RECIPE, recipe.getName()); // Recipe Name
        values.put(INFO_RECIPE, recipe.getInfoText()); // Info text
        values.put(F_KEY_LINK, recipe.getIdIngredient()); // Reference to Link table where ingredients are stored


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

        cursor.close();
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = new ArrayList<Recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + "recipe";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3));

                // Adding Recipe to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return Recipe list
        return recipeList;
    }


    // Link table methods


    public void addLink(Link link) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_LINK, link.getId()); // Link ID (id for Ingredient store to Recipe link table)
        values.put(F_KEY_RECIPE, link.getIdRecipe()); // Recipe id

        for (int i = 0; i < link.getListIngredient().length; i++) {


            if (link.getListIngredient()[i] != 0) {

                values.put("num" + String.valueOf(i + 1), link.getListIngredient()[i]);
            }

        }


        // Inserting Row
        db.insert(TABLE_LINK, null, values);
        db.close(); // Closing database connection
    }


    public List<Link> getAllLink() {


        int linkId;
        int idRecipe;


        List<Link> linkList = new ArrayList<Link>();
        // Select All Query
        String selectQuery = "SELECT * FROM link";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                int numArray[] = new int[30];
                //  Link link = new Link(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2),
                //    cursor.getInt(3));

                linkId = cursor.getInt(0);
                idRecipe = cursor.getInt(1);

                for (int i = 0; i < 29; i++) {
                    numArray[i] = cursor.getInt(i + 2);
                }



                Link link = new Link(linkId, idRecipe, numArray);

                // Adding Link to list
                linkList.add(link);

                //Arrays.fill(numArray,0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        db.close();

        // return Link list
        return linkList;
    }

    public String getIngredientName(int id) {

        String name = "";

        SQLiteDatabase db = this.getReadableDatabase();

        String querry = "Select name FROM ingredient WHERE id =" + String.valueOf(id);

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.moveToFirst()) {
            do {

                name = cursor.getString(0);


            } while (cursor.moveToNext());
        }

        // return recipe


        cursor.close();

        db.close();

        return name;


    }


    public int getRecipeId(String name) {

        int id = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String querry = "Select id FROM recipe WHERE name = '" + name + "'";

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.moveToFirst()) {
            do {

                id = cursor.getInt(0);


            } while (cursor.moveToNext());
        }

        // return recipe


        cursor.close();

        db.close();

        return id;


    }



    public List<String> getAllRecipeNames() {
        List<String> recipeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT name FROM " + "recipe";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String recipeName = cursor.getString(0);

                // Adding Ingredient to list
                recipeList.add(recipeName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list

        db.close();
        return recipeList;
    }

    public boolean deleteIngredient(int id) {

        boolean inRecipe = false;

        Ingredient ingredient;

        ingredient = getIngredient(id);

        if (ingredient.isInRecipe()) {

            Log.d("DELETE ERR", "Cant delete an Ingredient already in a recipe");

            inRecipe = true;
        } else {

            SQLiteDatabase db = getWritableDatabase();
            db.beginTransaction();


            try {


                db.execSQL("DELETE FROM ingredient WHERE id = " + String.valueOf(id));

                db.setTransactionSuccessful();

                inRecipe = false;
            } catch (Exception e) {
                Log.d("Exception", "Exeption while trying to delete Ingredient");
            } finally {
                db.endTransaction();
            }
        }

        return inRecipe;
    }

    public void setIngredient_InRecipe(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE ingredient SET inRecipe = 1  WHERE id = " + String.valueOf(id));



        db.close();


    }

    public void setIngredientInStock(int id, boolean value) {


        int tiny = IngredientUtility.convertBoolean(value);


        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE ingredient SET inStock =" + tiny + " WHERE id = " + String.valueOf(id));

        db.close();


    }



    public int getIngredientId(String name) {

        int id = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String querry = "Select id FROM ingredient WHERE name ='" + name + "'";

        Cursor cursor = db.rawQuery(querry, null);

        if (cursor.moveToFirst()) {
            do {

                id = cursor.getInt(0);


            } while (cursor.moveToNext());
        }

        // return recipe


        cursor.close();

        db.close();

        return id;


    }


    public int[] getRecipeIds(int ingredientId) {


        int recipeids[] = new int[30];

        SQLiteDatabase db = this.getReadableDatabase();

        //select idRecipe from link where num2 = 2;

        for (int i = 1; i < 30; i++) {

            String querry = " select idRecipe from link where num" + i + "= " + String.valueOf(ingredientId);

            Cursor cursor = db.rawQuery(querry, null);

            if (cursor.moveToFirst()) {
                do {

                    recipeids[i] = cursor.getInt(0);


                } while (cursor.moveToNext());
            }

            // return recipe

            cursor.close();
        }


        db.close();

        return recipeids;


    }


    public void deleteRecipeAndLink(int recipeId) {


        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();


        try {


            db.execSQL("DELETE FROM link WHERE IdRecipe = " + String.valueOf(recipeId));
            db.execSQL("DELETE FROM recipe WHERE id = " + String.valueOf(recipeId));


            db.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("Exception", "Exeption while trying to delete Recipe");
        } finally {
            db.endTransaction();
        }
    }






    // Utility methods

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

    public Link getLinkIngredient(int recipeID) {


        int linkId;
        int idRecipe;

        Link link = null;


        // Select All Query
        String selectQuery = "SELECT * FROM link where IdRecipe = " + String.valueOf(recipeID);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                int numArray[] = new int[30];
                //  Link link = new Link(cursor.getInt(0), cursor.getInt(1),cursor.getInt(2),
                //    cursor.getInt(3));

                linkId = cursor.getInt(0);
                idRecipe = cursor.getInt(1);

                for (int i = 0; i < 29; i++) {
                    numArray[i] = cursor.getInt(i + 2);
                }


                link = new Link(linkId, idRecipe, numArray);

                // Adding Link to list


                //Arrays.fill(numArray,0);
            } while (cursor.moveToNext());
        }

        cursor.close();

        db.close();

        // return Link list
        return link;
    }

    public int getMaxLinkId() {


        int max = 0;

        String countQuery = "SELECT MAX(IdLink) FROM link";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        max = cursor.getCount();
        cursor.close();

        return max;
    }

    public String checkIfRecipeNameExists(String name) {
        String recipeName = "";

        String selectQuery = "SELECT name FROM recipe WHERE name = '" + name + "'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                recipeName = cursor.getString(0);

            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list

        db.close();
        return recipeName;
    }

    public String getRecipeInfo(String name) {
        String recipeInfo = "";

        String selectQuery = "SELECT infoText FROM recipe WHERE name = '" + name + "'" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                recipeInfo = cursor.getString(0);

            } while (cursor.moveToNext());
        }

        cursor.close();
        // return contact list

        db.close();
        return recipeInfo;
    }


}
