package sp_coding.myapplication.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by woojen on 2018-02-07.
 */

public class DBHandler extends SQLiteOpenHelper {

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


    // Recipe Table Columns names
    private static final String KEY_ID2 = "id";
    private static final String KEY_NAME2 = "name";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Ingredient_TABLE = "CREATE TABLE " + TABLE_INGREDIENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";

        db.execSQL(CREATE_Ingredient_TABLE);
        String CREATE_Recipe_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," + KEY_NAME2 + " TEXT" + ")";


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


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void addIngredient(Ingredient ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, ingredient.getId()); // Ingredient ID
        values.put(KEY_NAME, ingredient.getName()); // Ingredient Name


        // Inserting Row
        db.insert(TABLE_INGREDIENT, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
    public Ingredient getIngredient(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INGREDIENT, new String[]{KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ingredient ingredient = new Ingredient(cursor.getInt(0), cursor.getString(1));
        // return contact
        return ingredient;
    }
}
