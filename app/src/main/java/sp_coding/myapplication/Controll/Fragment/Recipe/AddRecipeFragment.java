package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-15.
 */

public class AddRecipeFragment extends Fragment implements Util {

    ArrayList<String> ingredientList;
    ArrayList<String> selectedIngredient;
    RecyclerView recyclerView;
    SpinnerDialog spinnerDialog;

    RecipeUtility recipeUtility;
    LinkUtility linkUtility;
    IngredientUtility ingredientUtility;

    EditText inputName;
    EditText inputInfo;

    String currentItem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_fragment, container, false);

        ingredientList = new ArrayList<>();


        Ini(v);
        IniUtilityClass();

        initItems();

        createSpinner();

        recipeUtility.tempTest();

        Button ok = v.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = false;

                for (String s : selectedIngredient) {

                    if (s.equalsIgnoreCase(currentItem)) {

                        flag = true;

                    }

                }


                if (!flag) {
                    selectedIngredient.add(currentItem);
                }

                //ingredientList.remove(currentItem);
                //ingredientList.trimToSize();


                //createSpinner();



            }
        });

        Button addIngredientButton = v.findViewById(R.id.add_ingredient_for_recipe);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        Ini(v);

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

        Button saveButton = v.findViewById(R.id.createRecipeBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                size30();

                recipeUtility.newRecipe(selectedIngredient, inputName, inputInfo);
                //refreshIngredientField();


                recipeUtility.logRecipe();

                //linkUtility.logLinkList(); //
                linkUtility.logLinkListv2();

                RecipeFragment recipeFragment = new RecipeFragment();
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame, recipeFragment);
                fragmentTransaction1.commit();

            }
        });

        return v;
    }

    private void initItems() {

        ingredientList = (ArrayList<String>) ingredientUtility.getCompleteNameList();

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void IniUtilityClass() {
        recipeUtility = new RecipeUtility();
        linkUtility = new LinkUtility();
        ingredientUtility = new IngredientUtility();

        recipeUtility.setContext(this.getContext());
        linkUtility.setContext(this.getContext());
        ingredientUtility.setContext(this.getContext());
    }

    public void Ini(View v) {


        inputName = v.findViewById(R.id.nameInput);
        inputInfo = v.findViewById(R.id.infoInput);
        ingredientList = new ArrayList<>();
        selectedIngredient = new ArrayList<>();




    }

    public void refreshIngredientField() {


        ingredientList.clear();
        ingredientList.trimToSize();


    }

    private void size30() {


        for (int i = selectedIngredient.size(); i < 30; i++) {

            selectedIngredient.add("");

        }
    }

    public void createSpinner() {


        spinnerDialog = new SpinnerDialog(getActivity(), ingredientList, "Select Ingredient");


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(getActivity(), "Selected: " + item, Toast.LENGTH_SHORT).show();


                currentItem = item;

                //ingredientList.remove(item);


            }
        });
    }

}




