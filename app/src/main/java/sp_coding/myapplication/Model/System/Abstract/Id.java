package sp_coding.myapplication.Model.System.Abstract;

/**
 * Created by woojen on 2018-02-25.
 */

public abstract class Id {
    protected int id; // Reference to itself

    protected Id(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
