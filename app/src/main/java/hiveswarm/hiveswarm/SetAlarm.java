package hiveswarm.hiveswarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Set;

public class SetAlarm extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private int finalHour;
    private int finalMin;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(SetAlarm.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 0, alarmIntent, 0);

        TimePicker alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        final EditText numbOfWeeks = (EditText) findViewById(R.id.Frequency_of_alarm);


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
        finalHour = hour;
        finalMin = min;


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (numbOfWeeks.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        start();
                    }



                //We need to be able to make a notification pop up
                //Make them set the start date, and It will keep reminding them every 2 weeks
                //Also during the different seasons, summer, winter, spring, fall --> Ask the beekeeper if they want to change the reminder date.
            }
        });


    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000*60*60*24*14;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();

        /* Set the alarm to start at Time set on the clock */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, finalHour);
        calendar.set(Calendar.MINUTE, finalMin);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000*60*60*24*14, pendingIntent);
    }


}
