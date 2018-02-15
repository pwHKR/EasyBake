package sp_coding.myapplication.Controll.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import sp_coding.myapplication.R;

/**
 * Created by andreas on 2018-02-15.
 */

public class AddRecipeFragment extends Fragment {

    EditText inputName;
    EditText inputInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_recipe_fragment, container, false);

        inputName = (EditText) v.findViewById(R.id.nameInput);
        inputInfo = (EditText) v.findViewById(R.id.infoInput);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        Toolbar myToolbar = (Toolbar) v.findViewById(R.id.my_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        Button button = (Button) v.findViewById(R.id.createRecipeBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
