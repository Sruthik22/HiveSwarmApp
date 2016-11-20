package beekeepersjournal.beekeepersjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class PreferencesChange extends AppCompatActivity {

    public static final String tableName = "Settings";
    public static final String hiveNumber = "hiveNumber";
    public static final String framesPerHive = "framesPerHive";
    public static final String hiveBodies = "hiveBodies";
    public static final String supers = "supers";
    public static final String location = "location";
    public static final String yearsOfBeekeeping = "yearsOfBeekeeping";
    public static final String username = "Username";

    public final static ParseObject SettingsData = new ParseObject(tableName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_change);

        Parse.initialize(new Parse.Configuration.Builder(this) .applicationId("UdWhIAwhRmgyxKrR6xNEPwaNzMI008RBQOrAgJNd") .clientKey("keHYPaMOZR5hYTBZFL7JYD1lZAeCXlpszTkn9E9T") .server("https://parseapi.back4app.com/").build() );
        Button savePreferences = (Button) findViewById(R.id.btnSave);
        savePreferences.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Location = (EditText) findViewById(R.id.Location);
                String LocationString = Location.getText().toString();

                EditText NumberOfHives = (EditText) findViewById(R.id.NumberOfHives);
                int NumberOfHivesInt = Integer.parseInt(NumberOfHives.getText().toString());

                EditText FramesPerHive = (EditText) findViewById(R.id.FramesPerHive);
                int FramesPerHiveInt = Integer.parseInt(FramesPerHive.getText().toString());

                EditText HiveBodies = (EditText) findViewById(R.id.HiveBodies);
                int HiveBodiesInt = Integer.parseInt(HiveBodies.getText().toString());

                EditText YearsOfBeekeeping = (EditText) findViewById(R.id.YearsOfBeekeeping);
                int YearsOfBeekeepingInt = Integer.parseInt(YearsOfBeekeeping.getText().toString());

                EditText Supers = (EditText) findViewById(R.id.Supers);
                int SupersInt = Integer.parseInt(Supers.getText().toString());

                if (NumberOfHivesInt != 0 || FramesPerHiveInt != 0 || HiveBodiesInt != 0 || SupersInt != 0) {
                    SettingsData.put(username, "Sruthi");
                    SettingsData.put(hiveNumber, NumberOfHivesInt);
                    SettingsData.put(framesPerHive, FramesPerHiveInt);
                    SettingsData.put(hiveBodies, HiveBodiesInt);
                    SettingsData.put(supers, SupersInt);
                    SettingsData.put(location, LocationString);
                    SettingsData.put(yearsOfBeekeeping, YearsOfBeekeepingInt);

                    SettingsData.saveInBackground();

                    Intent launchIntent = new Intent();
                    launchIntent.setClass(getApplicationContext(), EnvironmentalConditions.class);
                    startActivity(launchIntent);
                }

                else {
                    Toast.makeText(PreferencesChange.this, "Please Fill in All Required Fields", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
