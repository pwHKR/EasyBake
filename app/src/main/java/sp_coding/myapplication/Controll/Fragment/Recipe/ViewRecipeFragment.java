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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
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

    private ShareDialog shareDialog;
    private CallbackManager callBackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_recipe_fragment, container, false);

        FacebookSdk.setApplicationId("1820733634893943");
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

        callBackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(getActivity());

        final Button shareButton = v.findViewById(R.id.shareBtn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareDialog.registerCallback(callBackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });

                String formattedString = ingredientArray.toString()
                        .replace(",", ", ")  //remove the commas
                        .replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim();           //remove trailing spaces from partially initialized arrays

                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setQuote("Just created a recipe using EasyBake!" +
                                "\n" + "Recipe Name: " + title.getText() + "\n" + "Information: "
                                + info.getText() + "\n" + "Ingredients: " + formattedString + "\n" +
                                        "\n" + "Download link below:")
                        .setContentUrl(Uri.parse("http://www.mediafire.com/file/vcz0uh71rjz0kr0/EasyBake.apk"))
                        .build();

                if(shareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(shareLinkContent);
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

}
