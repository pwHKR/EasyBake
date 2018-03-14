package sp_coding.myapplication.Controll.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sp_coding.myapplication.Model.Utility.Ingredient.IngredientUtility;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class HomeFragment extends Fragment implements Util {

    ArrayList<String> recipeList = new ArrayList<String>();
    RecipeUtility recipeUtility;
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        //Add recipe names to ArrayList
        //recipeList = (ArrayList<String>) recipeUtility.getCompleteNameList();
        ListView listView = v.findViewById(R.id.recipeListView);

        //For Testing ListView function
        String[] items={"Test1","Test2","Test3"};
        recipeList=new ArrayList<String>(Arrays.asList(items));
        //For testing ListView function

        //Add info to adapter
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
        //Apply adapter to ListView
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void IniUtilityClass() {

        recipeUtility = new RecipeUtility();

        recipeUtility.setContext(this.getContext());

    }
}
