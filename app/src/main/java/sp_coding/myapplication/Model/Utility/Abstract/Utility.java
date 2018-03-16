package sp_coding.myapplication.Model.Utility.Abstract;

import android.content.Context;

import sp_coding.myapplication.Model.DB.DBHandler;

/**
 * Created by woojen on 2018-02-26.
 * <p>
 * Use childs classes with Util Interface on fragments or activity's
 */

public abstract class Utility {

    private Context context;


    protected DBHandler dbh;

    public void setContext(Context context) {
        this.context = context;

        //dbh = new DBHandler(context);

        dbh = DBHandler.getInstance(context);
    }


    // Currently not used since auto increment is handeld by sqlite. Keep for now if needed later on
    protected int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }

}
