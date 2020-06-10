package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class add_raspberry_pi extends AppCompatActivity {

    // Declare Variables
    public static List<raspberry_pi_connection> ob;
    ProgressDialog hive_id_progress_dialog;
    private String HIVE_ID;

    public String ip_address_entered;

    public static final String tableName = "raspberry_pi_connection";
    public static final String username = "Username";
    public static final String raspberry_pi_address = "pi_ip";
    public final static ParseObject raspberry_pi_connection = ParseObject.create(tableName);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_raspberry_pi);

        Intent raspberry_pi_pair = getIntent();

        HIVE_ID = raspberry_pi_pair.getStringExtra("HIVE_ID");

        Button pair_button = (Button) findViewById(R.id.pair);
        final EditText ipaddress_edit_text = (EditText) findViewById(R.id.ip_address);

        pair_button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        //This is the ip_address they entered, now we have to check if a raspberry pi has this ip address
                        ip_address_entered = ipaddress_edit_text.getText().toString();
                        new RPi_Task().execute();
                    }
                });
    }


    //The Background Task
    private class RPi_Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            hive_id_progress_dialog = ProgressDialog.show(add_raspberry_pi.this, "Getting Raspberry Pi Data", "Retreiving Data...", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Making a query to the Database checking for a raspberry pi with the ip address that was entered
                ParseQuery<raspberry_pi_connection> raspberrypi_query = ParseQuery.getQuery(raspberry_pi_connection.class);
                raspberrypi_query.whereEqualTo(raspberry_pi_address, ip_address_entered);
                raspberrypi_query.whereEqualTo("Username", SignInActivity.sign_email);
                raspberrypi_query.whereEqualTo(GeneralObservationsActivity.hiveId, HIVE_ID);
                ob = raspberrypi_query.find();

                // If ob isn't empty then check if the record has a person associated with it.

                if (ob.isEmpty()) {
                    //No one is using this raspberry pi, so we can just add this ip_address to all of this users data
                    Log.e("IP_ADDRESS", "No one is using this raspberry pi, so we can just add this ip_address to all of this users data");

                    raspberry_pi_connection.put(username, SignInActivity.sign_email);
                    raspberry_pi_connection.put(GeneralObservationsActivity.hiveId, HIVE_ID);
                    raspberry_pi_connection.put(raspberry_pi_address, ip_address_entered);
                    raspberry_pi_connection.saveInBackground();
                }

                else {
                    //Someone is already associated with that ip_address, meaning that the raspberry pi is already used, give an error
                    Log.e("IP_ADDRESS", "Someone is already associated with the account");
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Close the progressdialog
            hive_id_progress_dialog.dismiss();

            finish();
            //After this, the beekeeper shouldn't be able to add another raspberry pi, to their hive.
        }
    }

}
