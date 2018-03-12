package sp_coding.myapplication.Controll.Fragment.Ingredient;


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
import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.R;


public class IngredientFragment extends Fragment implements Util {

    IngredientUtility ingredientUtility;
    List<String> ingredientList;
    SpinnerDialog spinnerDialog;
    boolean buttonFlag;  // False if delete button, true if set in stock button
    boolean checkboxValue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, false);


        IniUtilityClass();
        ingredientList = ingredientUtility.getCompleteNameList();

        ingredientUtility.logIngredient();

        final CheckBox checkBox = v.findViewById(R.id.checkBox3);

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


        Button stockButton = v.findViewById(R.id.inStock);
        stockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonFlag = true;

                checkboxValue = checkBox.isChecked();

                spinnerDialog.showSpinerDialog();
            }
        });

        Button removeIngredient = v.findViewById(R.id.deleteIngredient);
        removeIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();

                buttonFlag = false;

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


                if (!buttonFlag) {

                    ingredientUtility.delete(item);

                    IngredientFragment ingredientFragmentR = new IngredientFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, ingredientFragmentR);
                    fragmentTransaction.commit();
                } else {


                    ingredientUtility.setinStock(item, checkboxValue);


                    IngredientFragment ingredientFragmentR = new IngredientFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, ingredientFragmentR);
                    fragmentTransaction.commit();

                }



            }
        });
    }
}
