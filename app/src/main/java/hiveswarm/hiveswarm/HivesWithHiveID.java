package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HivesWithHiveID extends AppCompatActivity {

    public static String Hive_ID;
    // Declare Variables
    ListView listview;
    public static List<hive_data> ob;
    public static List<raspberry_pi_connection> raspberry_pi_connection_ob;
    public static List<environmental> environmental_ob;
    ProgressDialog hive_id_progress_dialog;
    HiveIdAdapter adapter;
    EnvironmentalAdapter environmentalAdapter;
    private List<HiveDataListItem> recordList;
    private List<EnvironmentalListItem> environmental_recordList;
    public String ip_address_of_pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hives_with_hive_id);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));


        //Runs a background task that displays the Hives with the Hive_ID that you've clicked on to display
        new HiveIdTask().execute();

        //Gets the intent that brought us to this activity
        Intent i = getIntent();

        // Get's the Hive_ID we clicked on in the previous activity
        Hive_ID = i.getStringExtra("Hive_ID");

        //If there is a raspberry pi, then you have to read that data as well


    }

    //The Background Task
    private class HiveIdTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            hive_id_progress_dialog = ProgressDialog.show(HivesWithHiveID.this, "Getting Hive Data", "Retreiving Data...", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            recordList = new ArrayList<HiveDataListItem>();
            try {
                //Making a query to the Database checking for the current username and Hive_ID
                ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
                query.whereEqualTo("Username", SignInActivity.sign_email);
                query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
                query.orderByAscending("createdAt");
                ob = query.find();

                //Iterating through the list of hive_data items setting a date when they were created and putting it in a lable. Then adding it to the array which adds it to the screen.
                for (hive_data hive_data : ob) {
                    HiveDataListItem map = new HiveDataListItem();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(hive_data.getCreatedAt());
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);

                    map.setObjectId(hive_data.getObjectId());

                    map.setCreationDate(String.valueOf(month + 1) + " - " + String.valueOf(day) + " - " + String.valueOf(year));
                    map.setHive_Id((String) hive_data.get(GeneralObservationsActivity.hiveId));
                    recordList.add(map);
                }

                //I also have to add the raspberry pi data.
                //This raspberry pi data is different than the main data
                //Now we need to get the ip_address associated with the hive_id and get all the records from the environmental database

                ParseQuery<raspberry_pi_connection> raspberrypi_query = ParseQuery.getQuery(raspberry_pi_connection.class);
                raspberrypi_query.whereEqualTo("Username", SignInActivity.sign_email);
                raspberrypi_query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
                try {
                    if (!raspberrypi_query.find().isEmpty()) {
                        ip_address_of_pi = raspberrypi_query.find().get(0).getString(add_raspberry_pi.raspberry_pi_address);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                environmental_recordList = new ArrayList<EnvironmentalListItem>();

                ParseQuery<environmental> environmental_query = ParseQuery.getQuery(environmental.class);
                environmental_query.whereEqualTo("ip_address", ip_address_of_pi);
                environmental_query.orderByAscending("createdAt");
                environmental_ob = environmental_query.find();

                for (environmental environmental_data : environmental_ob) {
                    EnvironmentalListItem environmental_map = new EnvironmentalListItem();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(environmental_data.getCreatedAt());
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);

                    environmental_map.setObjectId(environmental_data.getObjectId());

                    environmental_map.setCreationDate(String.valueOf(month + 1) + " - " + String.valueOf(day) + " - " + String.valueOf(year));
                    environmental_map.setIp_address(ip_address_of_pi);
                    environmental_recordList.add(environmental_map);
                }

            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //At the end we give it the recordList array which has all the records to be added and give it to the adapter which will ultimately place it on the screen
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into HiveIdAdapter.java

            adapter = new HiveIdAdapter(HivesWithHiveID.this, recordList);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);

            // Close the progressdialog
            hive_id_progress_dialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //We will create a file, or if otherwise there read it, if it says true, then can't go to the menu option

        ParseQuery<raspberry_pi_connection> raspberrypi_query = ParseQuery.getQuery(raspberry_pi_connection.class);
        raspberrypi_query.whereEqualTo("Username", SignInActivity.sign_email);
        raspberrypi_query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
        try {
            raspberry_pi_connection_ob = raspberrypi_query.find();
            if (raspberry_pi_connection_ob.isEmpty()) {
                getMenuInflater().inflate(R.menu.add_raspberry_pi, menu);
            }
            else {
                getMenuInflater().inflate(R.menu.data_show, menu);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }



        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_raspberry_pi:
                    // Go to the add raspberry pi user interface where you can enter the ip address of your pi
                    Intent i = new Intent(this, add_raspberry_pi.class);
                    i.putExtra("HIVE_ID", Hive_ID);
                    startActivity(i);
                    return true;
                case R.id.rpi_data:
                    // Check out the raspberry pi data
                    listview = (ListView) findViewById(R.id.listview);
                    // Pass the results into HiveIdAdapter.java
                    environmentalAdapter = new EnvironmentalAdapter(HivesWithHiveID.this, environmental_recordList);
                    // Binds the Adapter to the ListView
                    listview.setAdapter(environmentalAdapter);
                    return true;
                case R.id.hive_data:
                    // Check out the hive swarm entered data
                    // Check out the raspberry pi data
                    listview = (ListView) findViewById(R.id.listview);
                    // Pass the results into HiveIdAdapter.java
                    adapter = new HiveIdAdapter(HivesWithHiveID.this, recordList);
                    // Binds the Adapter to the ListView
                    listview.setAdapter(adapter);
                    return true;
                case R.id.add_to_do:
                    //Bring to the To-Do List for the Hive
                    Intent to_do_list = new Intent(this, ToDoList.class);
                    to_do_list.putExtra("Hive_ID", Hive_ID);
                    startActivity(to_do_list);
                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
        }

}
