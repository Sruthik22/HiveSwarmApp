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

    public int getAcetonePPM() {
        return getInt("acetone_ppm");
    }

    public int getPropanePPM() {
        return getInt("propane_ppm");
    }

    public int getAmmoniaPPM() {
        return getInt("ammonia_ppm");
    }

    public int getMethlyPPM() {
        return getInt("methly_ppm");
    }

    public int getHydrogenPPM() { return getInt("hydrogen_ppm");}

    public int getLPGPPM() { return getInt("lpg_ppm");}

    public int getCarbonMonoxidePPM() { return getInt("carbon_monoxide_ppm");}

    public int getMethanePPM() { return getInt("methane_ppm");}

    public int getPM10() { return getInt("pm_10");}
}
