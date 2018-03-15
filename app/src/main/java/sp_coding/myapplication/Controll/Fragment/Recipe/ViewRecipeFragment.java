package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sp_coding.myapplication.Controll.Fragment.Home.HomeFragment;
import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.Model.Utility.Recipe.RecipeUtility;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-03-15.
 */

public class ViewRecipeFragment extends Fragment implements Util {

    TextView title;
    TextView info;
    ListView ingredientList;

    RecipeUtility recipeUtility;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_recipe_fragment, container, false);

        Ini(v);
        IniUtilityClass();

        Bundle bundle = getArguments();

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
        ingredientList = v.findViewById(R.id.listView);

    }

    @Override
    public void IniUtilityClass() {

        recipeUtility = new RecipeUtility();

        recipeUtility.setContext(this.getContext());

    }
}
