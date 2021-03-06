package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    private ArrayList<String> ingredientList;
    private ArrayList<String> ingredientsForListView;
    private ArrayList<String> selectedIngredient;
    private SpinnerDialog spinnerDialog;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String checkedRecipeName;

    private RecipeUtility recipeUtility;
    private LinkUtility linkUtility;
    private IngredientUtility ingredientUtility;

    private EditText inputName;
    private EditText inputInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_fragment, container, false);

        ingredientList = new ArrayList<>();


        Ini(v);
        IniUtilityClass();

        initItems();

        createSpinner();



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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog alertDialog;

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                alertDialogBuilder.setTitle("Delete From Recipe");

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ingredientsForListView.remove(position);
                        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.txtitem, ingredientsForListView);
                        listView.setAdapter(adapter);


                    }
                });

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });

        Button saveButton = v.findViewById(R.id.createRecipeBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkedRecipeName = recipeUtility.checkRecipeName(inputName.getText().toString());

                if (inputName.getText().toString().isEmpty()) {

                    final AlertDialog alertDialog;

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getActivity());

                    alertDialogBuilder.setTitle("Warning!");
                    alertDialogBuilder.setMessage("You need to enter a recipe name!");

                    alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AddRecipeFragment addRecipeFragment = new AddRecipeFragment();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, addRecipeFragment);
                            fragmentTransaction.commit();
                        }
                    });

                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                } else if (inputName.getText().toString().equals(checkedRecipeName)) {

                    final AlertDialog alertDialog;

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getActivity());

                    alertDialogBuilder.setTitle("Warning!");
                    alertDialogBuilder.setMessage("A recipe with that name already exists");

                    alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    size30();

                    recipeUtility.newRecipe(ingredientsForListView, inputName, inputInfo);


                    recipeUtility.logRecipe();


                    linkUtility.logLinkListv2();

                    RecipeFragment recipeFragment = new RecipeFragment();
                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, recipeFragment);
                    fragmentTransaction1.commit();

                    BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }

            }
        });

        return v;
    }

    private void initItems() {

        ingredientList = (ArrayList<String>) ingredientUtility.getCompleteNameList();

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

    private void Ini(View v) {


        inputName = v.findViewById(R.id.nameInput);
        inputInfo = v.findViewById(R.id.infoInput);
        ingredientList = new ArrayList<>();
        selectedIngredient = new ArrayList<>();
        listView = v.findViewById(R.id.ingredientListView);
        ingredientsForListView = new ArrayList<>();


    }


    private void size30() {


        for (int i = selectedIngredient.size(); i < 30; i++) {

            ingredientsForListView.add("");

        }
    }

    private void createSpinner() {


        spinnerDialog = new SpinnerDialog(getActivity(), ingredientList, "Select Ingredient");


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(getActivity(), "Selected: " + item, Toast.LENGTH_SHORT).show();

                if (ingredientsForListView.contains(item)) {
                    final AlertDialog alertDialog;

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            getActivity());

                    alertDialogBuilder.setTitle("Alert!");
                    alertDialogBuilder.setMessage("The ingredient has already been added");

                    alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    ingredientsForListView.add(item);
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtitem, ingredientsForListView);
                    listView.setAdapter(adapter);
                }

                //currentItem = item;

                //ingredientList.remove(item);


            }
        });
    }

}




