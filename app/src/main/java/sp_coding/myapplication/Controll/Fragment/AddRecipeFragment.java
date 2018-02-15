package sp_coding.myapplication.Controll.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

        Toolbar myToolbar = v.findViewById(R.id.my_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        Button button = v.findViewById(R.id.createRecipeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newRecipe();




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

        String numArray[] = new String[30];

        numArray[0] = ingredient1.toString();
        numArray[1] = ingredient2.toString();

        Recipe recipe = new Recipe(getNewID("recipe"), inputName.toString(), inputInfo.toString());

        Link link = new Link(getNewID("link"), recipe.getId(), numArray);

        recipe.setIdIngredient(link.getIdLink());

    }
}
