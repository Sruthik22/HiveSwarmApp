package hiveswarm.hiveswarm;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("settings")

public class settings extends ParseObject {

    public settings() {
        // A default constructor is required.
    }

    public int getHiveNumber() {
        return getInt(PreferencesView.hiveNumber);
    }

    public int getFramesPerHive() {
        return getInt(PreferencesView.framesPerHive);
    }

    public int getHiveBodies() {
        return getInt(PreferencesView.hiveBodies);
    }

    public int getSupers() {
        return getInt(PreferencesView.supers);
    }

    public int getYearsofBeekeeping() {
        return getInt(PreferencesView.yearsOfBeekeeping);
    }

    public String getLocation() {
        return getString(PreferencesView.location);
    }

}