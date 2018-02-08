package sp_coding.myapplication.Model;

/**
 * Created by woojen on 2018-02-07.
 */

public class Logic {


    public Logic() {


    }

    public boolean convertTinyInt(int tinyInt) {


        boolean result = false;


        if (tinyInt == 0) {

            result = false;
        }

        if (tinyInt == 1) {

            result = true;
        }

        return result;

    }


}

