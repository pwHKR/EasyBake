package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-15.
 */

public class AddRecipeFragment extends Fragment implements Util {

    ArrayList<EditText> ingredientList;
    RecipeUtility recipeUtility;
    LinkUtility linkUtility;


    EditText inputName;
    EditText inputInfo;
    EditText ingredient1;
    EditText ingredient2;
    EditText ingredient3;
    EditText ingredient4;
    EditText ingredient5;
    EditText ingredient6;
    EditText ingredient7;
    EditText ingredient8;
    EditText ingredient9;
    EditText ingredient10;
    EditText ingredient11;
    EditText ingredient12;
    EditText ingredient13;
    EditText ingredient14;
    EditText ingredient15;
    EditText ingredient16;
    EditText ingredient17;
    EditText ingredient18;
    EditText ingredient19;
    EditText ingredient20;
    EditText ingredient21;
    EditText ingredient22;
    EditText ingredient23;
    EditText ingredient24;
    EditText ingredient25;
    EditText ingredient26;
    EditText ingredient27;
    EditText ingredient28;
    EditText ingredient29;
    EditText ingredient30;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_fragment, container, false);

        ingredientList = new ArrayList<>();

        IniUtilityClass();





        inputName = v.findViewById(R.id.nameInput);
        inputInfo = v.findViewById(R.id.infoInput);
        ingredient1 = v.findViewById(R.id.ingredient1);
        ingredientList.add(ingredient1);
        ingredient2 = v.findViewById(R.id.ingredient2);
        ingredientList.add(ingredient2);
        ingredient3 = v.findViewById(R.id.ingredient3);
        ingredientList.add(ingredient3);
        ingredient4 = v.findViewById(R.id.ingredient4);
        ingredientList.add(ingredient4);
        ingredient5 = v.findViewById(R.id.ingredient5);
        ingredientList.add(ingredient5);
        ingredient6 = v.findViewById(R.id.ingredient6);
        ingredientList.add(ingredient6);
        ingredient7 = v.findViewById(R.id.ingredient7);
        ingredientList.add(ingredient7);
        ingredient8 = v.findViewById(R.id.ingredient8);
        ingredientList.add(ingredient8);
        ingredient9 = v.findViewById(R.id.ingredient9);
        ingredientList.add(ingredient9);
        ingredient10 = v.findViewById(R.id.ingredient10);
        ingredientList.add(ingredient10);
        ingredient11 = v.findViewById(R.id.ingredient11);
        ingredientList.add(ingredient11);
        ingredient12 = v.findViewById(R.id.ingredient12);
        ingredientList.add(ingredient12);
        ingredient13 = v.findViewById(R.id.ingredient13);
        ingredientList.add(ingredient13);
        ingredient14 = v.findViewById(R.id.ingredient14);
        ingredientList.add(ingredient14);
        ingredient15 = v.findViewById(R.id.ingredient15);
        ingredientList.add(ingredient15);
        ingredient16 = v.findViewById(R.id.ingredient16);
        ingredientList.add(ingredient16);
        ingredient17 = v.findViewById(R.id.ingredient17);
        ingredientList.add(ingredient17);
        ingredient18 = v.findViewById(R.id.ingredient18);
        ingredientList.add(ingredient18);
        ingredient19 = v.findViewById(R.id.ingredient19);
        ingredientList.add(ingredient19);
        ingredient20 = v.findViewById(R.id.ingredient20);
        ingredientList.add(ingredient20);
        ingredient21 = v.findViewById(R.id.ingredient21);
        ingredientList.add(ingredient21);
        ingredient22 = v.findViewById(R.id.ingredient22);
        ingredientList.add(ingredient22);
        ingredient23 = v.findViewById(R.id.ingredient23);
        ingredientList.add(ingredient23);
        ingredient24 = v.findViewById(R.id.ingredient24);
        ingredientList.add(ingredient24);
        ingredient25 = v.findViewById(R.id.ingredient25);
        ingredientList.add(ingredient25);
        ingredient26 = v.findViewById(R.id.ingredient26);
        ingredientList.add(ingredient26);
        ingredient27 = v.findViewById(R.id.ingredient27);
        ingredientList.add(ingredient27);
        ingredient28 = v.findViewById(R.id.ingredient28);
        ingredientList.add(ingredient28);
        ingredient29 = v.findViewById(R.id.ingredient29);
        ingredientList.add(ingredient29);
        ingredient30 = v.findViewById(R.id.ingredient30);
        ingredientList.add(ingredient30);


        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        final Toolbar myToolbar = v.findViewById(R.id.my_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

        Button exitButton = v.findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // testName();
                linkUtility.logLinkListv2();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
                bottomNavigationView.setVisibility(View.VISIBLE);

                RecipeFragment recipeFragment = new RecipeFragment();
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame, recipeFragment);
                fragmentTransaction1.commit();

            }
        });

        Button button = v.findViewById(R.id.createRecipeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                recipeUtility.newRecipe(ingredientList, inputName, inputInfo);
                refreshIngredientField();


                recipeUtility.logRecipe();

                //linkUtility.logLinkList(); //
                // linkUtility.logLinkListv2();



            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




    public void refreshIngredientField() {


        ingredientList.clear();
        ingredientList.trimToSize();


        ingredientList.add(ingredient1);

        ingredientList.add(ingredient2);

        ingredientList.add(ingredient3);

        ingredientList.add(ingredient4);

        ingredientList.add(ingredient5);

        ingredientList.add(ingredient6);

        ingredientList.add(ingredient7);

        ingredientList.add(ingredient8);

        ingredientList.add(ingredient9);

        ingredientList.add(ingredient10);

        ingredientList.add(ingredient11);

        ingredientList.add(ingredient12);

        ingredientList.add(ingredient13);

        ingredientList.add(ingredient14);

        ingredientList.add(ingredient15);

        ingredientList.add(ingredient16);

        ingredientList.add(ingredient17);

        ingredientList.add(ingredient18);

        ingredientList.add(ingredient19);

        ingredientList.add(ingredient20);

        ingredientList.add(ingredient21);

        ingredientList.add(ingredient22);

        ingredientList.add(ingredient23);

        ingredientList.add(ingredient24);

        ingredientList.add(ingredient25);

        ingredientList.add(ingredient26);

        ingredientList.add(ingredient27);

        ingredientList.add(ingredient28);

        ingredientList.add(ingredient29);

        ingredientList.add(ingredient30);


    }


    @Override
    public void IniUtilityClass() {
        recipeUtility = new RecipeUtility();
        linkUtility = new LinkUtility();

        recipeUtility.setContext(this.getContext());
        linkUtility.setContext(this.getContext());
    }
}

