package sp_coding.myapplication.Controll.Fragment.Ingredient;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import sp_coding.myapplication.Model.System.Main.Ingredient;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;
import sp_coding.myapplication.R;


public class IngredientFragment extends Fragment implements Util {

    private IngredientUtility ingredientUtility;
    private LinkUtility linkUtility;
    private List<String> ingredientList;
    private SpinnerDialog spinnerDialog;
    private String selectedItem;
    private boolean inRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, false);


        IniUtilityClass();
        ingredientList = ingredientUtility.getCompleteNameList();

        ingredientUtility.logIngredient();

        refreshIngredientStatus();

        createSpinner();

        // testX();

        Button addIngredientButton = v.findViewById(R.id.addIngredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIngredientFragment addIngredientFragment = new AddIngredientFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, addIngredientFragment);
                fragmentTransaction.commit();
            }
        });


        Button removeIngredient = v.findViewById(R.id.manageIngredients);
        removeIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();

            }
        });

        return v;
    }


    @Override
    public void IniUtilityClass() {
        ingredientUtility = new IngredientUtility();

        ingredientUtility.setContext(this.getContext());

        linkUtility = new LinkUtility();

        linkUtility.setContext(this.getContext());
    }


    private void createSpinner() {


        spinnerDialog = new SpinnerDialog(getActivity(), (ArrayList<String>) ingredientList, "Select Ingredient");


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(getActivity(), "Selected: " + item, Toast.LENGTH_SHORT).show();

                selectedItem = item;

                final AlertDialog alertDialog;

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                alertDialogBuilder.setTitle("Choose Option");

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        IngredientFragment ingredientFragmentR = new IngredientFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, ingredientFragmentR);
                        fragmentTransaction.commit();
                    }
                });

                CharSequence[] choices = {" In Stock ", " Not In Stock ", " Delete "};

                alertDialogBuilder.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {

                        switch (choice) {
                            case 0:
                                ingredientUtility.setinStock(selectedItem, true);
                                //Log.d("Test", "case1");
                                AlertinStock(selectedItem);

                                break;
                            case 1:
                                ingredientUtility.setinStock(selectedItem, false);
                                //  Log.d("Test", "case2");
                                AlertNotInStock(selectedItem);
                                break;
                            case 2:
                                inRecipe = ingredientUtility.delete(selectedItem);

                                if (inRecipe) {
                                    AlertToManyRecipes(selectedItem, inRecipe);
                                } else {
                                    AlertDeleteOk(selectedItem);
                                }


                                refreshFragment();
                                //Log.d("Test", "case3");
                                break;

                        }

                        dialog.dismiss();
                    }
                });

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    private void refreshFragment() {
        IngredientFragment ingredientFragment = new IngredientFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, ingredientFragment);
        fragmentTransaction.commit();
    }




    private void refreshIngredientStatus() {

        List<Ingredient> list = ingredientUtility.getCompleteList();

        for (Ingredient i : list) {

            ingredientUtility.xInRecipe(i.getId());
        }

    }

    private void AlertToManyRecipes(String name, boolean inRecipe) {

        int id;
        int amount;

        if (inRecipe) {

            id = ingredientUtility.getId(name);

            amount = ingredientUtility.xInRecipe(id);

            final AlertDialog alertDialog;

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            alertDialogBuilder.setTitle("Warning!");
            alertDialogBuilder.setMessage("This Ingredient is used in " + amount + " Recipes.\nYou need to delete those recipes before you can delete this ingredient");

            alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            alertDialog = alertDialogBuilder.create();
            alertDialog.show();


        }

    }


    private void AlertinStock(String item) {


        final AlertDialog alertDialog;

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(item + " is in stock");

        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    private void AlertNotInStock(String item) {


        final AlertDialog alertDialog;

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(item + " is no longer in stock");

        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    private void AlertDeleteOk(String item) {


        final AlertDialog alertDialog;

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(item + " deleted");

        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

}





