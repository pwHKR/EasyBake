package sp_coding.myapplication.Model.Utility.Link;

import android.util.Log;

import java.util.List;

import sp_coding.myapplication.Model.System.Main.Link;
import sp_coding.myapplication.Model.Utility.Abstract.Utility;

/**
 * Created by woojen on 2018-02-26.
 *
 * Use with Util Interface on fragments or activity's
 */

public class LinkUtility extends Utility {

    public void logLinkList() {


        List<Link> list = dbh.getAllLink();


        for (Link link : list) {

            // Log.d("Link ID ", link.getId()+"\n");
            link.ListIterator();

        }
    }

    public void logLinkListv2() {


        List<Link> list = dbh.getAllLink();


        for (Link link : list) {


            Log.d("Link ID " + link.getId(), "-----------\n");


            for (int i : link.getListIngredient()) {


                if (i != 0)

                    Log.d("Included", dbh.getIngredientName(i) + "\n");


            }


        }
    }

}