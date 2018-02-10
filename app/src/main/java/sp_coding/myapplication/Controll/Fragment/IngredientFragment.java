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

import sp_coding.myapplication.Model.DBHandler;
import sp_coding.myapplication.Model.Ingredient;
import sp_coding.myapplication.Model.Logic;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class IngredientFragment extends Fragment {

    EditText editText;
    CheckBox checkBox;
    DBHandler dbh;
    Logic logic;

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

                Log.d("Complete Table INGRE..", dbh.getAllIngredients().toString());


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

        newId = getNewID("ingredient");

        Ingredient ingredient = new Ingredient(newId, name, inStockTinyInt);


        dbh.addIngredient(ingredient);
    }

    private int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }


}
