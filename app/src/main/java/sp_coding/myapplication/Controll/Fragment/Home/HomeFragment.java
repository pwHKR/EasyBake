package sp_coding.myapplication.Controll.Fragment.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        IniUtilityClass();

        //Add recipe names to ArrayList
        recipeList = (ArrayList<String>) recipeUtility.getCompleteNameList();
        ListView listView = v.findViewById(R.id.recipeListView);

        //Add info to adapter
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtitem, recipeList);
        //Apply adapter to ListView
        listView.setAdapter(adapter);

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
