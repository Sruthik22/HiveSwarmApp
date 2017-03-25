package hiveswarm.hiveswarm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Environmental")

public class environmental extends ParseObject {

    public environmental() {
        // A default constructor is required.
    }

    public int getHumidity() {
        return getInt("humidity");
    }

    public int getTemperature() {
        return getInt("temperature");
    }
}
