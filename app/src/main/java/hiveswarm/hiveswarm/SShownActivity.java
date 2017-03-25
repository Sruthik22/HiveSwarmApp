package hiveswarm.hiveswarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SShownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sshown);

        TextView NumberofSupersinPlace = (TextView) findViewById(R.id.NumberofSupersinPlace);

        if (String.valueOf(HiveInformation.hive_data.getSupersInPlace()).equals(null)) {
            NumberofSupersinPlace.setText("");
            HiveInformation.values.add("");
        } else {
            NumberofSupersinPlace.setText(String.valueOf(HiveInformation.hive_data.getSupersInPlace()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getSupersInPlace()));
        }


        TextView NumberOfSupersAdded = (TextView) findViewById(R.id.NumberOfSupersAdded);

        if (String.valueOf(HiveInformation.hive_data.getSupersAdded()).equals(null)) {
            NumberOfSupersAdded.setText("");
            HiveInformation.values.add("");
        } else {
            NumberOfSupersAdded.setText(String.valueOf(HiveInformation.hive_data.getSupersAdded()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getSupersAdded()));
        }
    }
}
