package sp_coding.myapplication.Controll.Fragment.Ingredient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class AddIngredientFragment extends Fragment implements Util {

    boolean isTrue;
    IngredientUtility ingredientUtility;

    List<Ingredient> allIngredientList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_ingredient_fragment, container, false);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        final EditText editText = v.findViewById(R.id.editText);
        final CheckBox checkBox = v.findViewById(R.id.checkBox);
        allIngredientList = new ArrayList<>();
        IniUtilityClass();
        Button send = v.findViewById(R.id.add_ingredient);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isTrue = checkBox.isChecked();


                ingredientUtility.NewIngredient(editText.getText().toString(), isTrue);

                allIngredientList = ingredientUtility.getCompleteList();

                ingredientUtility.logIngredient();




            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void IniUtilityClass() {


        ingredientUtility = new IngredientUtility();
        ingredientUtility.setContext(this.getContext());

    }


}
