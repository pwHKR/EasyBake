package sp_coding.myapplication.Model.Utility;

/**
 * Created by woojen on 2018-02-07.
 */

public class Logic {


    public Logic() {


    }

    // Convert Int to Boolean

    public boolean convertTinyInt(int tinyInt) {


        boolean result;


        result = tinyInt != 0;

        return result;

    }

    // Convert boolean to Int

    public int convertBoolean(boolean input) {


        int result = 0;

        String StringValue = String.valueOf(input);


        if (StringValue.equalsIgnoreCase("false")) {

            result = 0;
        }

        if (StringValue.equalsIgnoreCase("true")) {

            result = 1;
        }

        return result;

    }


}

