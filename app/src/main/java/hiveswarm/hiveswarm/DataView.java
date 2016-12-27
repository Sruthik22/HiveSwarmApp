package hiveswarm.hiveswarm;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseQuery;


public class DataView extends Fragment {

    // Declare Variables
    ListView listview;
    public static List<hive_data> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<HiveDataListItem> hiveDataList;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_data_view, container, false);
        this.rootView = rootView;
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
        return rootView;
    }


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("Getting Data from DB");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            hiveDataList = new ArrayList<HiveDataListItem>();
            try {
                ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
                query.whereEqualTo("Username", SignInActivity.Display_Name);
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
                    hiveDataList.add(map);
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
            listview = (ListView) rootView.findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(getContext(),
                    hiveDataList);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}

