package hiveswarm.hiveswarm;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class SetAlarm extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        TimePicker alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);

        int hour = 0;
        int min = 0;

        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            hour = alarmTimePicker.getHour();
            min = alarmTimePicker.getMinute();
        } else {
            hour = alarmTimePicker.getCurrentHour();
            min = alarmTimePicker.getCurrentMinute();
        }
        final Button button = (Button) findViewById(R.id.start_alarm);
        final int finalHour = hour;
        final int finalMin = min;

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Check your Hive!");
                i.putExtra(AlarmClock.EXTRA_HOUR, finalHour);
                i.putExtra(AlarmClock.EXTRA_MINUTES, finalMin);
                startActivity(i);
            }
        });


    }


}
