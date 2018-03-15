package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import sp_coding.myapplication.Controll.Fragment.Home.HomeFragment;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Link.LinkUtility;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-03-15.
 */

public class ViewRecipeFragment extends Fragment implements Util {

    TextView title;
    TextView info;
    ListView ingredientList;

    ArrayList ingredientArray;
    ArrayAdapter<String> adapter;

    RecipeUtility recipeUtility;
    LinkUtility linkUtility;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_recipe_fragment, container, false);

        Ini(v);
        IniUtilityClass();

        Bundle bundle = getArguments();

        ingredientArray = linkUtility.getRecipeIngredients(bundle.getString("recipeName"));
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtitem, ingredientArray);
        ingredientList.setAdapter(adapter);

        info.setMovementMethod(new ScrollingMovementMethod());

        title.setText(bundle.getString("recipeName"));
        info.setText(recipeUtility.getRecipeInfoText(bundle.getString("recipeName")));

        Button exitBtn = v.findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, homeFragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void Ini(View v) {

        title = v.findViewById(R.id.title);
        info = v.findViewById(R.id.info);
        ingredientList = v.findViewById(R.id.ingredientListView);
        ingredientArray = new ArrayList();

    }

    @Override
    public void IniUtilityClass() {

        recipeUtility = new RecipeUtility();
        linkUtility = new LinkUtility();

        recipeUtility.setContext(this.getContext());
        linkUtility.setContext(this.getContext());

    }
}
