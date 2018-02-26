package sp_coding.myapplication.Model.System.Abstract;

/**
 * Created by woojen on 2018-02-25.
 */

public abstract class Id_Name extends Id {
    protected String name;

    protected Id_Name(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
