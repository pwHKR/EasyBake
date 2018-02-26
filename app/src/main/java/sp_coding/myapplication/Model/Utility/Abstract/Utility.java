package sp_coding.myapplication.Model.Utility.Abstract;

import android.content.Context;

import sp_coding.myapplication.Model.DB.DBHandler;

/**
 * Created by woojen on 2018-02-26.
 * Must set context in order for db object to work
 */

public abstract class Utility {

    protected Context context;


    protected DBHandler dbh;

    public void setContext(Context context) {
        this.context = context;

        dbh = new DBHandler(context);
    }

    protected int getNewID(String table) {

        int result = dbh.getCount(table) + 1;

        return result;
    }

}
