package hiveswarm.hiveswarm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("to_do_list")

public class to_do_list extends ParseObject {

    public to_do_list() {
        // A default constructor is required.
    }

    public String getToDo() {
        return getString("to_do");
    }
}
