package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    public static ListView lvItems;
    // Declare Variables
    public static List<to_do_list> ob;
    ProgressDialog hive_id_progress_dialog;
    ToDoListAdapter adapter;
    private List<ToDoListItem> recordList;
    private String Hive_ID;

    public static final String tableName = "to_do_list";
    public final static ParseObject to_do_list = ParseObject.create(tableName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        lvItems = (ListView) findViewById(R.id.listview);

        Intent i = getIntent();
        Hive_ID = i.getStringExtra("Hive_ID");

        new HiveIdTask().execute();


    }

    public void onAddItem(View v) {
        //We need to add an item to the database and then notify the adapter that something has changed
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();

        to_do_list.put("username", SignInActivity.sign_email);
        to_do_list.put("Hive_ID", Hive_ID);
        to_do_list.put("to_do", itemText);

        ToDoListItem toDoListItem = new ToDoListItem();
        toDoListItem.setTo_do_title(itemText);
        recordList.add(toDoListItem);
        lvItems.setAdapter(adapter);

        to_do_list.saveInBackground();

        etNewItem.setText("");

    }

    //The Background Task
    private class HiveIdTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            hive_id_progress_dialog = ProgressDialog.show(ToDoList.this, "Getting To-Do List", "Retreiving Data...", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            recordList = new ArrayList<ToDoListItem>();
            try {
                //Making a query to the Database checking for the current username and Hive_ID
                ParseQuery<to_do_list> query = ParseQuery.getQuery(to_do_list.class);
                query.whereEqualTo("username", SignInActivity.sign_email);
                query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);
                query.orderByAscending("createdAt");
                ob = query.find();

                //Iterating through the list of hive_data items setting a date when they were created and putting it in a lable. Then adding it to the array which adds it to the screen.
                for (to_do_list todos : ob) {
                    ToDoListItem map = new ToDoListItem();

                    map.setTo_do_title(todos.getToDo());
                    map.setObjectId(todos.getObjectId());
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
            //At the end we give it the recordList array which has all the records to be added and give it to the adapter which will ultimately place it on the screen
            // Locate the listview in listview_main.xml
            // Pass the results into HiveIdAdapter.java

            adapter = new ToDoListAdapter(ToDoList.this, recordList, Hive_ID);
            // Binds the Adapter to the ListView
            lvItems.setAdapter(adapter);

            // Close the progressdialog
            hive_id_progress_dialog.dismiss();
        }
    }
}


