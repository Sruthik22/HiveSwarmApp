package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HivesWithHiveID extends AppCompatActivity {

    public static String Hive_ID;
    // Declare Variables
    ListView listview;
    public static List<hive_data> ob;
    ProgressDialog hive_id_progress_dialog;
    HiveIdAdapter adapter;
    private List<HiveDataListItem> recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hives_with_hive_id);

        // Execute RemoteDataTask AsyncTask
        new HiveIdTask().execute();

        Intent i = getIntent();

        // Get the result of rank
        Hive_ID = i.getStringExtra("Hive_ID");


    }

    // RemoteDataTask AsyncTask
    private class HiveIdTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            hive_id_progress_dialog = ProgressDialog.show(HivesWithHiveID.this, "Getting Hive Data with Hive Id", "Retreiving Data...", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            recordList = new ArrayList<HiveDataListItem>();
            try {
                ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
                query.whereEqualTo("Username", SignInActivity.sign_email);
                query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
                query.orderByAscending("createdAt");
                ob = query.find();

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
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into HiveIdAdapter.java
            adapter = new HiveIdAdapter(HivesWithHiveID.this,
                    recordList);
            // Binds the Adapter to the ListView
             listview.setAdapter(adapter);
            // Close the progressdialog
            hive_id_progress_dialog.dismiss();
        }
    }
}
