package hiveswarm.hiveswarm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("raspberry_pi_connection")

public class raspberry_pi_connection extends ParseObject {

    public raspberry_pi_connection() {
        // A default constructor is required.
    }

    public int getHumidity() {
        return getInt("humidity");
    }

    public int getTemperature() {
        return getInt("temperature");
    }
}
