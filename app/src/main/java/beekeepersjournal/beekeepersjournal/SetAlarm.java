package beekeepersjournal.beekeepersjournal;

import android.support.v7.app.AppCompatActivity;

public class SetAlarm extends AppCompatActivity {
    /*TimePicker alarm_timepicker;
    public static PendingIntent pending_intent;
    int choose_whale_sound;

    //to make our alarm manager
    public static AlarmManager alarm_manager;

    public static String hour_string;
    public static String minute_string;
    Context context;

    public static int hour;
    public static int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        this.context = this;

        //Initialize our AlarmManager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Initialize our Timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        final Calendar calendar = Calendar.getInstance();

        // create an intent to the Alarm Receiver class
        final Intent my_intent = new Intent();
        final Intent broad_cast_intent = new Intent(this.context, Alarm_Receiver.class);

        Button setAlarmButton = (Button) findViewById(R.id.setAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // get the int values of the hour and minute

                hour = alarm_timepicker.getHour();
                minute = alarm_timepicker.getMinute();

                // convert the int values to strings
                hour_string = String.valueOf(hour);
                minute_string = String.valueOf(minute);

                // put in extra string into my_intent
                // tells the clock that you pressed the "alarm on" button
                broad_cast_intent.putExtra("extra", "alarm on");

                // put in an extra int into my_intent
                // tells the clock that you want a certain value from the drop-down menu/spinner
                broad_cast_intent.putExtra("whale_choice", choose_whale_sound);

                // create a pending intent that delays the intent
                // until the specified calendar time
                pending_intent = PendingIntent.getBroadcast(SetAlarm.this, 0,
                        broad_cast_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);

                my_intent.setClass(getApplicationContext(), Home.class);

                my_intent.putExtra("wentToHome", "False");
                startActivity(my_intent);
            }
        });
    }*/
}
