package sp_coding.myapplication.Model.Utility.Abstract;

import android.content.Context;

import sp_coding.myapplication.Model.DB.DBHandler;

/**
 * Created by woojen on 2018-02-26.
 *
 * Use childs classes with Util Interface on fragments or activity's
 *
 */

public abstract class Utility {

    protected Context context;


    protected DBHandler dbh;

    public void setContext(Context context) {
        this.context = context;

        //dbh = new DBHandler(context);

        dbh = DBHandler.getInstance(context);
    }

    protected int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }

}
