package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class HiveInformation extends AppCompatActivity {
    // Declare Variables
    public static String Hive_ID;
    String CreationDate;
    public static String objectId;
    List<hive_data> ob;
    ProgressDialog mProgressDialog;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private static boolean first_time = true;

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "You are already on this Page!", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.environment:
                        Intent environment_intent = new Intent();
                        environment_intent.setClass(getApplicationContext(), EShownActivity.class);
                        startActivity(environment_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pest:
                        Intent pests_intent = new Intent();
                        pests_intent.setClass(getApplicationContext(), PShownActivity.class);
                        startActivity(pests_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.disease:
                        Intent disease_intent = new Intent();
                        disease_intent.setClass(getApplicationContext(), DShownActivity.class);
                        startActivity(disease_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.queen:
                        Intent queen_intent = new Intent();
                        queen_intent.setClass(getApplicationContext(), EQShownActivity.class);
                        startActivity(queen_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.eat:
                        Intent eat_intent = new Intent();
                        eat_intent.setClass(getApplicationContext(), FShownActivity.class);
                        startActivity(eat_intent);
                        drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
        tv_email.setText(SignInActivity.sign_email);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public static hive_data hive_data;

    public static List<String> values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive_information);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        Intent i = getIntent();
        // Get the result of rank

        Hive_ID = i.getStringExtra("Hive_ID");

        values.add(Hive_ID);

        ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
        query.whereEqualTo("Username", SignInActivity.sign_email);
        query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);

        try {
            ob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (first_time) {
            if (ob.size() > 1) {
                //Instead of going here we need intermitent screen to see if there are multiple records with one hive id

            }

            //otherwise just get first record
            hive_data = ob.get(0);
            first_time = false;
        }

        // Locate the TextViews in content_main.xml
        TextView txthive = (TextView) findViewById(R.id.hive_Id);

        // Set results to the TextViews
        txthive.setText(Hive_ID);

        TextView pollenAmount = (TextView) findViewById(R.id.pollenAmount);


        if (hive_data.getPollenAmount().equals("")) {
            pollenAmount.setText("");
            values.add("");
        } else {
            pollenAmount.setText(hive_data.getPollenAmount());
            values.add(hive_data.getPollenAmount());
        }


        TextView honeyAmount = (TextView) findViewById(R.id.honeyAmount);


        if (hive_data.getAmountOfHoney().equals(null)) {
            honeyAmount.setText("");
            values.add("");
        } else {
            honeyAmount.setText(hive_data.getAmountOfHoney());
            values.add(hive_data.getAmountOfHoney());
        }

        TextView beeCount = (TextView) findViewById(R.id.beeCount);


        if (hive_data.getPestCount().equals(null)) {
            beeCount.setText("");
            values.add("");
        } else {
            beeCount.setText(hive_data.getBeeCount());
            values.add(hive_data.getBeeCount());
        }

        TextView emergency = (TextView) findViewById(R.id.emergency);


        if (String.valueOf(hive_data.getEmergency()).equals(null)) {
            emergency.setText("");
            values.add("");
        } else {
            emergency.setText(String.valueOf(hive_data.getEmergency()));
            values.add(String.valueOf(hive_data.getEmergency()));
        }

        TextView swarm = (TextView) findViewById(R.id.swarm);


        if (String.valueOf(hive_data.getSwarm()).equals(null)) {
            swarm.setText("");
            values.add("");
        } else {
            swarm.setText(String.valueOf(hive_data.getSwarm()));
            values.add(String.valueOf(hive_data.getSwarm()));
        }

        TextView TemperOfBees = (TextView) findViewById(R.id.TemperOfBees);

        if (String.valueOf(hive_data.getTemper()).equals(null)) {
            TemperOfBees.setText("");
            values.add("");
        } else {
            TemperOfBees.setText(String.valueOf(hive_data.getTemper()));
            values.add(String.valueOf(hive_data.getTemper()));
        }


        TextView ColonyStrength = (TextView) findViewById(R.id.ColonyStrength);

        if (String.valueOf(hive_data.getColonyCondition()).equals(null)) {
            ColonyStrength.setText("");
            values.add("");
        } else {
            ColonyStrength.setText(String.valueOf(hive_data.getColonyCondition()));
            values.add(String.valueOf(hive_data.getColonyCondition()));
        }


        TextView ActionsNotes = (TextView) findViewById(R.id.ActionsNotes);

        if (String.valueOf(hive_data.getActionsAndNotes()).equals(null)) {
            ActionsNotes.setText("");
            values.add("");
        } else {
            ActionsNotes.setText(String.valueOf(hive_data.getActionsAndNotes()));
            values.add(String.valueOf(hive_data.getActionsAndNotes()));
        }


    }

    /*

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Share:
                saveToFile("File Name");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveToFile(String fileName) {

        new SaveToCSV().execute();
    }

    // RemoteDataTask AsyncTask
    private class SaveToCSV extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(HiveInformation.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Saving to CSV File");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            String columnString = "";
            String dataString = "";


            int i = 0;

            for (Field field : R.string.class.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers()) && field.getType().equals(int.class)) {
                    try {
                        if (field.getName().startsWith("hs_")) {
                            i += 1;
                            int id = field.getInt(null);
                            columnString += "\"" + getResources().getString(id) + "\",";
                            dataString += "\"" + values.get(i - 1) + "\",";
                        }
                    } catch (IllegalArgumentException e) {
                        // ignore
                    } catch (IllegalAccessException e) {
                        // ignore
                    }
                }
            }

            String combinedString = columnString + "\n" + dataString;

            File file = null;
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()) {
                File dir = new File(root.getAbsolutePath() + "/PersonData");
                dir.mkdirs();
                file = new File(dir, "Data.csv");
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    out.write(combinedString.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (file != null) {
                Log.e("not nl", "");
            } else {
                Log.e("not nl", "yes");
            }

            Uri u1 = Uri.fromFile(file);

            Log.e("HiveInformation", "In Try: Going to Send Intent");

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Hive Data CSV File - Created at - " + CreationDate);
            sendIntent.putExtra(Intent.EXTRA_STREAM, u1);
            sendIntent.setType("text/html");
            startActivity(sendIntent);

            file.delete();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Email client show.
            mProgressDialog.dismiss();
        }
    }*/
}
