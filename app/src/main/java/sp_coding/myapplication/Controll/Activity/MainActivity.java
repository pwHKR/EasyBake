package sp_coding.myapplication.Controll.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import sp_coding.myapplication.Controll.Fragment.HomeFragment;
import sp_coding.myapplication.Controll.Fragment.IngredientFragment;
import sp_coding.myapplication.Controll.Fragment.RecipeFragment;
import sp_coding.myapplication.Model.DB.DBHandler;
import sp_coding.myapplication.R;

public class MainActivity extends AppCompatActivity {


    DBHandler dbh = new DBHandler(this);




    public void TestDB() {




       /* Ingredient ingredient1 = new Ingredient(0, "Morot", true);
        Ingredient ingredient2 = new Ingredient(1, "Apelsin", false);

        Ingredient morotTest;
        Ingredient apelsinTest;

        dbh.addIngredient(ingredient1);
        dbh.addIngredient(ingredient2);


        morotTest = dbh.getIngredient(0);
        apelsinTest = dbh.getIngredient(1);

        Log.d("READING FROM DB:", morotTest.toString());
        Log.d("READING FROM DB2:", apelsinTest.toString());

        Recipe recipe1 = new Recipe(0, "recept1");
        Recipe recipe2 = new Recipe(1, "recept2");

        dbh.addRecipe(recipe1);
        dbh.addRecipe(recipe2);

        Recipe rec1Test;
        Recipe rec2Test;

        rec1Test = dbh.getRecipe(0);
        rec2Test = dbh.getRecipe(1);

        Log.d("Reading from DB table 2", rec1Test.toString());
        Log.d("Reading from DB table 2", rec2Test.toString());

*/

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, homeFragment);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:

                    RecipeFragment recipeFragment = new RecipeFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, recipeFragment);
                    fragmentTransaction1.commit();

                    return true;

                case R.id.navigation_ingredients:
                    IngredientFragment ingredientFragment = new IngredientFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, ingredientFragment);
                    fragmentTransaction2.commit();

                    return true;

            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, homeFragment);
        fragmentTransaction.commit();

        TestDB();
    }


}
