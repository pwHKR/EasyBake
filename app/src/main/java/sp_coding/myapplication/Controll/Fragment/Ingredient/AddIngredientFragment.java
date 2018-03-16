package sp_coding.myapplication.Controll.Fragment.Ingredient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class AddIngredientFragment extends Fragment implements Util {

    private boolean isTrue;
    private IngredientUtility ingredientUtility;
    private RecipeUtility recipeUtility;
    private LinkUtility linkUtility;

    private List<Ingredient> allIngredientList;

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

        Button cancelBtn = v.findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //recipeUtility.delete(); // test metod for delete recipe

                //linkUtility.printIngredients("second");


                goBack();
            }
        });


        return v;
    }


    public void IniUtilityClass() {

        recipeUtility = new RecipeUtility();
        recipeUtility.setContext(this.getContext());

        ingredientUtility = new IngredientUtility();
        ingredientUtility.setContext(this.getContext());

        linkUtility = new LinkUtility();
        linkUtility.setContext(this.getContext());

    }

    // Return to ingredient fragment
    public void goBack() {
        IngredientFragment ingredientFragment = new IngredientFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, ingredientFragment);
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }


}
