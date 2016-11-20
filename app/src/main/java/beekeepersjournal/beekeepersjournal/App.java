package beekeepersjournal.beekeepersjournal;

// App.java
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Hive_Data.class);
        Parse.initialize(new Parse.Configuration.Builder(this) .applicationId("UdWhIAwhRmgyxKrR6xNEPwaNzMI008RBQOrAgJNd") .clientKey("keHYPaMOZR5hYTBZFL7JYD1lZAeCXlpszTkn9E9T") .server("https://parseapi.back4app.com/").build() );
    }
}
