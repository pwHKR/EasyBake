package sp_coding.myapplication.Controll.Fragment.Ingredient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sp_coding.myapplication.Model.Utility.Interface.Util;
import sp_coding.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InStockFragment extends Fragment implements Util {


    public InStockFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_stock, container, false);
    }

    @Override
    public void IniUtilityClass() {

    }
}
