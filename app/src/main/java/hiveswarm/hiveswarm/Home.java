package hiveswarm.hiveswarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        FloatingActionButton addEntryButton = (FloatingActionButton) rootView.findViewById(R.id.addEntry);

        addEntryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        Intent launchIntent = new Intent();
                        launchIntent.setClass(getContext(), hiveswarm.hiveswarm.GeneralObservationsActivity.class);
                        startActivity(launchIntent);
                    }
                }).start();
            }
        });

        Button alarmSetter = (Button) rootView.findViewById(R.id.reminderAlarm);

        alarmSetter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        Intent launchIntent = new Intent();
                        launchIntent.setClass(getContext(), hiveswarm.hiveswarm.SetAlarm.class);
                        startActivity(launchIntent);
                    }
                }).start();
            }
        });

        /*TextView update_text;
        Context context;
        this.context = this;
        final Button turnOffAlarmClockButton = (Button) findViewById(R.id.turnOffAlarmClock);
        //Initialize our Update Box
        update_text = (TextView) findViewById(R.id.update_text);
        // convert 24-hour time to 12-hour time
        if (SetAlarm.hour > 12) {
            SetAlarm.hour_string = String.valueOf(SetAlarm.hour - 12);
        }
        if (SetAlarm.minute < 10) {
            //10:7 --> 10:07
            SetAlarm.minute_string = "0" + String.valueOf(SetAlarm.minute);
        }
        final Button setAlarmButton = (Button) findViewById(R.id.SetReminder);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent launchIntent = new Intent();
                launchIntent.setClass(getApplicationContext(), SetAlarm.class);
                startActivity(launchIntent);
            }
        });
        // method that changes the update text Textbox
        update_text.setText("Alarm set to: " + SetAlarm.hour_string + ":" + SetAlarm.minute_string);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            turnOffAlarmClockButton.setVisibility(View.VISIBLE);
            update_text.setVisibility(View.VISIBLE);
            turnOffAlarmClockButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SetAlarm.alarm_manager.cancel(SetAlarm.pending_intent);
                    turnOffAlarmClockButton.setVisibility(View.INVISIBLE);
                    update_text.setVisibility(View.INVISIBLE);
                    setAlarmButton.setVisibility(View.VISIBLE);
                }
            });
        }*/

        return rootView;
    }


}

