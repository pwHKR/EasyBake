package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-08.
 */

public class RecipeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recipe_fragment, container, false);
        Button button = v.findViewById(R.id.add_recipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRecipeFragment addRecipeFragment = new AddRecipeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, addRecipeFragment);
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
