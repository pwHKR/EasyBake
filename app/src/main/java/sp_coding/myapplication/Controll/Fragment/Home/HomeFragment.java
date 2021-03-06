package sp_coding.myapplication.Controll.Fragment.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sp_coding.myapplication.Controll.Fragment.Recipe.ViewRecipeFragment;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class HomeFragment extends Fragment implements Util {

    private ArrayList<String> recipeList = new ArrayList<String>();
    private RecipeUtility recipeUtility;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        IniUtilityClass();

        //Add recipe names to ArrayList

        recipeList = (ArrayList<String>) recipeUtility.isInStockList(true); // True if you want in stock list and false if you want not in stock
        //recipeList = (ArrayList<String>) recipeUtility.getCompleteNameList();

        Log.d("List", recipeUtility.isInStockList(true).toString());

        listView = v.findViewById(R.id.recipeListView);


        //Add info to adapter
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
        //Apply adapter to ListView
        listView.setAdapter(adapter);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("In stock ingredients recipes");
        list.add("All recipes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("In stock ingredients recipes")){
                    recipeList = (ArrayList<String>) recipeUtility.isInStockList(true);
                    adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
                    listView.setAdapter(adapter);
                } else if (parent.getItemAtPosition(position).toString().equals("All recipes")){
                    recipeList = (ArrayList<String>) recipeUtility.getCompleteNameList();
                    adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", parent.getItemAtPosition(position).toString());

                ViewRecipeFragment viewRecipeFragment = new ViewRecipeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                viewRecipeFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame, viewRecipeFragment);
                fragmentTransaction.commit();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog alertDialog;

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                alertDialogBuilder.setTitle("Delete Recipe");

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        recipeUtility.delete(recipeUtility.getRecipeID(parent.getItemAtPosition(position).toString()));
                        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
                        listView.setAdapter(adapter);
                        refreshFragment();

                    }
                });

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });

        return v;
    }

    private void refreshFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, homeFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void IniUtilityClass() {

        recipeUtility = new RecipeUtility();

        recipeUtility.setContext(this.getContext());

    }


}
