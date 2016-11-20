package beekeepersjournal.beekeepersjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FloatingActionButton addEntryButton = (FloatingActionButton) findViewById(R.id.addEntry);

        addEntryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        Intent launchIntent = new Intent();
                        launchIntent.setClass(getApplicationContext(), GeneralObservationsActivity.class);
                        startActivity(launchIntent);
                    }
                }).start();
            }
        });

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes final int tabId) {
                new Thread(new Runnable() {
                        public void run() {
                        if (tabId == R.id.home) {
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else if (tabId == R.id.analyze) {
                            startActivity(new Intent(getApplicationContext(), GraphDataActivity.class));
                        }
                        else if (tabId == R.id.settings) {
                            startActivity(new Intent(getApplicationContext(), PreferencesView.class));
                        }
                        else if (tabId == R.id.logout) {
                            startActivity(new Intent(getApplicationContext(), PreferencesView.class));
                        }
                    }
                }).start();

            }
        });

    }

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



    }

