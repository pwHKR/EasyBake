package sp_coding.myapplication.Controll.Fragment.Ingredient;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sp_coding.myapplication.R;


public class IngredientFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ingredient_fragment, container, false);

        Button button = v.findViewById(R.id.addIngredient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddIngredientFragment addIngredientFragment = new AddIngredientFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, addIngredientFragment);
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
