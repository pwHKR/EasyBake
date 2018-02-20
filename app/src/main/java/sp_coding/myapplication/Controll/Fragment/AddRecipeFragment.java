package sp_coding.myapplication.Controll.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import sp_coding.myapplication.Model.DB.DBHandler;
import sp_coding.myapplication.Model.Object.Link;
import sp_coding.myapplication.Model.Object.Recipe;
import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-15.
 */

public class AddRecipeFragment extends Fragment {

    EditText inputName;
    EditText inputInfo;
    EditText ingredient1;
    EditText ingredient2;
    DBHandler dbh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_fragment, container, false);

        inputName = v.findViewById(R.id.nameInput);
        inputInfo = v.findViewById(R.id.infoInput);
        ingredient1 = v.findViewById(R.id.ingredient1);
        ingredient2 = v.findViewById(R.id.ingredient2);

        dbh = new DBHandler(this.getContext());

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        final Toolbar myToolbar = v.findViewById(R.id.my_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        Button exitButton = v.findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
                bottomNavigationView.setVisibility(View.VISIBLE);

                RecipeFragment recipeFragment = new RecipeFragment();
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame, recipeFragment);
                fragmentTransaction1.commit();

            }
        });

        Button button = v.findViewById(R.id.createRecipeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newRecipe();

                logLink(dbh.getAllLink());
                logRecipe(dbh.getAllRecipes());







            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }

    private void newRecipe() {

        int numArray[] = new int[30];

        Log.d("ingre1", ingredient1.getText().toString());

        numArray[0] = Integer.parseInt(ingredient1.getText().toString());

        Log.d("intTest", String.valueOf(numArray[0]));
        numArray[1] = Integer.parseInt(ingredient2.getText().toString());

        Recipe recipe = new Recipe(getNewID("recipe"), inputName.getText().toString(), inputInfo.getText().toString());

        Link link = new Link(getNewID("link"), recipe.getId(), numArray);

        recipe.setIdIngredient(link.getIdLink());

        dbh.addRecipe(recipe);
        dbh.addLink(link);

    }


    private void logRecipe(List<Recipe> recipe) {

        int loopCount = 0;

        for (Recipe i : recipe) {

            loopCount++;

            Log.d("RecipeElement " + String.valueOf(loopCount) + ": ",
                    "name " + i.getName() + "\n" + "Info text: " + i.getInfoText() + "\n" + "id: " + i.getId() + "\n" + "IdLink: "
                            + i.getIdIngredient());
        }
    }

    private void logLink(List<Link> link) {


        int temp[] = new int[30];

        int loopCount = 0;

        for (Link i : link) {

            loopCount++;

            temp = i.getIngredientNum();

            Log.d("Link Element " + String.valueOf(loopCount) + ": ",
                    "id " + i.getIdLink() + "\n" + "Id Recipe " + i.getIdRecipe() + "\n" + "ingredient 1: " + temp[0] + "\n" + "Ingredient 2 "
                            + temp[1]);
        }
    }
}
