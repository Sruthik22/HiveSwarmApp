package hiveswarm.hiveswarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EnvironmentalConditions extends AppCompatActivity {


    public static final String temperature = "Weather_temperature";
    public static final String humidity = "Weather_humidity";
    public static final String precipitation = "Weather_precipitation";
    public static final String cloudiness = "Weather_cloudiness";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_conditions);

        Button launch = (Button) findViewById(R.id.btnSave);
        launch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText TemperatureEditText = (EditText) findViewById(R.id.TemperatureEditText);
                int IntTemperature;
                if (TemperatureEditText.getText().toString().equals("")) {
                    IntTemperature = 0;
                } else {
                    IntTemperature = Integer.parseInt(TemperatureEditText.getText().toString());
                }

                EditText PrecipitationEditText = (EditText) findViewById(R.id.PrecipitationEditText);


                int IntPrecipitation;
                if (PrecipitationEditText.getText().toString().equals("")) {
                    IntPrecipitation = 0;
                } else {
                    IntPrecipitation = Integer.parseInt(PrecipitationEditText.getText().toString());
                }

                EditText HumidityEditText = (EditText) findViewById(R.id.HumidityEditText);

                int IntHumidity;
                if (HumidityEditText.getText().toString().equals("")) {
                    IntHumidity = 0;
                } else {
                    IntHumidity = Integer.parseInt(HumidityEditText.getText().toString());
                }


                Spinner mySpinner = (Spinner) findViewById(R.id.CloudCoverageSpinner);
                String CloudCoverageString = mySpinner.getSelectedItem().toString();


                GeneralObservationsActivity.hiveData.put(temperature, IntTemperature);
                GeneralObservationsActivity.hiveData.put(humidity, IntHumidity);
                GeneralObservationsActivity.hiveData.put(precipitation, IntPrecipitation);
                GeneralObservationsActivity.hiveData.put(cloudiness, CloudCoverageString);

                Intent launchIntent = new Intent();
                launchIntent.setClass(getApplicationContext(), PestsDiseases.class);
                startActivity(launchIntent);
            }

        });

    }


}
