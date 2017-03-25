package hiveswarm.hiveswarm;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(hive_data.class);
        ParseObject.registerSubclass(settings.class);
        ParseObject.registerSubclass(environmental.class);


        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("UdWhIAwhRmgyxKrR6xNEPwaNzMI008RBQOrAgJNd").clientKey("keHYPaMOZR5hYTBZFL7JYD1lZAeCXlpszTkn9E9T").server("https://parseapi.back4app.com/").enableLocalDataStore().build());
    }
}
