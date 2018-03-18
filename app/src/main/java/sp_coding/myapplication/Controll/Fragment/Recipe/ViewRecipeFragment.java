package sp_coding.myapplication.Controll.Fragment.Recipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
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

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONObject;

import java.lang.annotation.Target;
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

    private TextView title;
    private TextView info;
    private ListView ingredientList;

    private ArrayList ingredientArray;
    private ArrayAdapter<String> adapter;

    private RecipeUtility recipeUtility;
    private LinkUtility linkUtility;

    ShareDialog shareDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_recipe_fragment, container, false);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);

        Ini(v);
        IniUtilityClass();

        Bundle bundle = getArguments();

        ingredientArray = linkUtility.getRecipeIngredients(bundle.getString("recipeName"));
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.txtitem, ingredientArray);
        ingredientList.setAdapter(adapter);

        info.setMovementMethod(new ScrollingMovementMethod());

        title.setText(bundle.getString("recipeName"));
        info.setText(recipeUtility.getRecipeInfoText(bundle.getString("recipeName")));

        Button shareButton = v.findViewById(R.id.shareBtn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Android Facebook Integration and Login Tutorial")
                            .setImageUrl(Uri.parse("https://www.studytutorial.in/ " +
                                    "wp-content/uploads/2017/02/FacebookLoginButton-min-300x136.png"))
                                            .setContentDescription(
                                                    "This tutorial explains how to integrate Facebook and Login " +
                                                            "through Android Application")
                                                            .setContentUrl(Uri.parse("https://www.studytutorial.in/ " +
                                                                    "android-facebook-integration-and-login-tutorial"))
                                                                            .build();
                    shareDialog.show(linkContent);  // Show facebook ShareDialog
                }
            }
        });

        Button exitBtn = v.findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
                bottomNavigationView.setVisibility(View.VISIBLE);

                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, homeFragment);
                fragmentTransaction.commit();

            }
        });

        return v;
    }


    private void Ini(View v) {

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

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
