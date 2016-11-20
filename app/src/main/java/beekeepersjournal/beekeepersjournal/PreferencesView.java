package beekeepersjournal.beekeepersjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

public class PreferencesView extends AppCompatActivity {

    public static final String tableName = "Settings";
    public static final String hiveNumber = "hiveNumber";
    public static final String framesPerHive = "framesPerHive";
    public static final String hiveBodies = "hiveBodies";
    public static final String supers = "supers";
    public static final String location = "location";
    public static final String yearsOfBeekeeping = "yearsOfBeekeeping";

    public final static ParseObject SettingsData = new ParseObject(tableName);

    public static boolean Preferences_Opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_view);

        if (Preferences_Opened == false) {
            Intent launchIntent = new Intent();
            launchIntent.setClass(getApplicationContext(), PreferencesChange.class);
            startActivity(launchIntent);
        }

        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("UdWhIAwhRmgyxKrR6xNEPwaNzMI008RBQOrAgJNd").clientKey("keHYPaMOZR5hYTBZFL7JYD1lZAeCXlpszTkn9E9T").server("https://parseapi.back4app.com/").build());

        TextView Location = (TextView) findViewById(R.id.LocationTextView);

        TextView NumberOfHives = (TextView) findViewById(R.id.NumberOfHivesTextView);

        TextView FramesPerHive = (TextView) findViewById(R.id.FramesPerHiveTextView);

        TextView HiveBodies = (TextView) findViewById(R.id.HiveBodiesTextView);

        TextView YearsOfBeekeeping = (TextView) findViewById(R.id.YearsOfBeekeepingTextView);

        TextView Supers = (TextView) findViewById(R.id.SupersTextView);

        String settingsHiveNumber = SettingsData.get(hiveNumber).toString();
        String settingsFrameNumber = SettingsData.get(framesPerHive).toString();
        String settingsHiveBodyNumber = SettingsData.get(hiveBodies).toString();
        String settingsSuperNumber = SettingsData.get(supers).toString();
        String settingsLocation = SettingsData.get(location).toString();
        String settingsYearsOfBeekeeping = SettingsData.get(yearsOfBeekeeping).toString();

        Location.setText(settingsLocation);
        NumberOfHives.setText(settingsHiveNumber);
        FramesPerHive.setText(settingsFrameNumber);
        HiveBodies.setText(settingsHiveBodyNumber);
        YearsOfBeekeeping.setText(settingsYearsOfBeekeeping);
        Supers.setText(settingsSuperNumber);

    }
}
