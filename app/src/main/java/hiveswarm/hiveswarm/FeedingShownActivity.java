package hiveswarm.hiveswarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FeedingShownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding_shown);

        TextView FeedBees = (TextView) findViewById(R.id.FeedBees);

        if (String.valueOf(HiveInformation.hive_data.getFeedingOrMedication()).equals(null)) {
            FeedBees.setText("");
            HiveInformation.values.add("");
        } else {
            FeedBees.setText(String.valueOf(HiveInformation.hive_data.getFeedingOrMedication()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFeedingOrMedication()));
        }

        TextView ReasonToFeedBees = (TextView) findViewById(R.id.ReasonToFeedBees);

        if (String.valueOf(HiveInformation.hive_data.getReasonForFeeding()).equals(null)) {
            ReasonToFeedBees.setText("");
            HiveInformation.values.add("");
        } else {
            ReasonToFeedBees.setText(String.valueOf(HiveInformation.hive_data.getReasonForFeeding()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getReasonForFeeding()));
        }

        TextView Frequency = (TextView) findViewById(R.id.Frequency);

        if (String.valueOf(HiveInformation.hive_data.getFrequencyOfFeeding()).equals(null)) {
            Frequency.setText("");
            HiveInformation.values.add("");
        } else {
            Frequency.setText(String.valueOf(HiveInformation.hive_data.getFrequencyOfFeeding()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFrequencyOfFeeding()));
        }

        TextView AmountFeeding = (TextView) findViewById(R.id.AmountFeeding);

        if (String.valueOf(HiveInformation.hive_data.getAmountOfFoodFed()).equals(null)) {
            AmountFeeding.setText("");
            HiveInformation.values.add("");
        } else {
            AmountFeeding.setText(String.valueOf(HiveInformation.hive_data.getAmountOfFoodFed()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getAmountOfFoodFed()));
        }

        TextView TypeOfFood = (TextView) findViewById(R.id.TypeOfFood);

        if (String.valueOf(HiveInformation.hive_data.getTypeOfFood()).equals(null)) {
            TypeOfFood.setText("");
            HiveInformation.values.add("");
        } else {
            TypeOfFood.setText(String.valueOf(HiveInformation.hive_data.getTypeOfFood()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getTypeOfFood()));
        }
    }
}
