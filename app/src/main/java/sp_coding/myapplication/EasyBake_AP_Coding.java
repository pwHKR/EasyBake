package sp_coding.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

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

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_bake__ap__coding);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        TestDB();
    }

}
