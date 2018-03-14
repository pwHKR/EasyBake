package sp_coding.myapplication.Controll.Fragment.Ingredient;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import sp_coding.myapplication.Controll.Activity.MainActivity;
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.R;


public class IngredientFragment extends Fragment implements Util {

    IngredientUtility ingredientUtility;
    List<String> ingredientList;
    SpinnerDialog spinnerDialog;
    String selectedItem;
    boolean buttonFlag;  // False if delete button, true if set in stock button

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, false);


        IniUtilityClass();
        ingredientList = ingredientUtility.getCompleteNameList();

        ingredientUtility.logIngredient();

        createSpinner();


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


        /*Button stockButton = v.findViewById(R.id.inStock);
        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonFlag = true;

                checkboxValue = checkBox.isChecked();

                spinnerDialog.showSpinerDialog();
            }
        });*/

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




    @Override
    public void IniUtilityClass() {
        ingredientUtility = new IngredientUtility();

        ingredientUtility.setContext(this.getContext());
    }


    public void createSpinner() {


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

                final CharSequence[] choices = {" In Stock "," Not In Stock "," Delete "};

                alertDialogBuilder.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int choice) {

                        switch(choice) {
                            case 0:
                                ingredientUtility.setinStock(selectedItem, true);
                                break;
                            case 1:
                                ingredientUtility.setinStock(selectedItem, false);
                                break;
                            case 2:
                                ingredientUtility.delete(selectedItem);
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
}
