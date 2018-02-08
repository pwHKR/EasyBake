package sp_coding.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import sp_coding.myapplication.Model.DBHandler;
import sp_coding.myapplication.Model.Ingredient;

public class EasyBake_AP_Coding extends AppCompatActivity {

    DBHandler dbh = new DBHandler(this);



    public void TestDB() {

        Ingredient ingredient1 = new Ingredient(0, "Morot");
        Ingredient ingredient2 = new Ingredient(1, "Apelsin");

        Ingredient morotTest;
        Ingredient apelsinTest;

        dbh.addIngredient(ingredient1);
        dbh.addIngredient(ingredient2);


        morotTest = dbh.getIngredient(0);
        apelsinTest = dbh.getIngredient(1);

        Log.d("READING FROM DB:", morotTest.toString());
        Log.d("READING FROM DB2:", apelsinTest.toString());


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
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_bake__ap__coding);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, homeFragment);
        fragmentTransaction.commit();

        TestDB();
    }
}
