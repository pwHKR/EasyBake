package sp_coding.myapplication.Controll.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.DB.DBHandler;
import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.Utility.Logic;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class IngredientFragment extends Fragment {


    DBHandler dbh;
    Logic logic;

    List<Ingredient> forTesting = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, false);

        final EditText editText = v.findViewById(R.id.editText);
        final CheckBox checkBox = v.findViewById(R.id.checkBox);
        dbh = new DBHandler(this.getContext());
        logic = new Logic();

        Button send = v.findViewById(R.id.add_ingredient);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isTrue;


                isTrue = checkBox.isChecked();


                Log.d("isChecked:", String.valueOf(isTrue));


                NewIngredient(editText.getText().toString(), isTrue);

                forTesting = dbh.getAllIngredients();

                logIngredient(forTesting);


            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void NewIngredient(String name, boolean inStock) {

        int newId;

        int inStockTinyInt = logic.convertBoolean(inStock);

        Log.d("FCT", String.valueOf(inStockTinyInt));

        newId = getNewID("ingredient");

        Ingredient ingredient = new Ingredient(newId, name, inStockTinyInt);


        dbh.addIngredient(ingredient);
    }

    private int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }


    // temp  Method used for testing db
    private void logIngredient(List<Ingredient> ingredients) {

        int loopCount = 0;

        for (Ingredient i : ingredients) {

            loopCount++;

            Log.d("Element " + String.valueOf(loopCount) + ": ",
                    "name: " + i.getName() + "\n" + "id: " + i.getId() + "\n" + "in stock: "
                            + i.isInStock());
        }


    }


}
