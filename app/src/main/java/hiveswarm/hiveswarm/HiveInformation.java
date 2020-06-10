package hiveswarm.hiveswarm;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class HiveInformation extends AppCompatActivity {
    // Declare Variables
    public static String Hive_ID;
    public static String objectId;
    List<hive_data> ob;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

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

    public static int Query_Value;

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
        Query_Value = i.getIntExtra("List Item", 0);

        ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
        query.whereEqualTo("Username", SignInActivity.sign_email);
        query.whereEqualTo(GeneralObservationsActivity.hiveId, Hive_ID);

        try {
            ob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        hive_data = ob.get(Query_Value);

        // Locate the TextViews in content_main.xml
        TextView txthive = (TextView) findViewById(R.id.hive_Id);

        // Set results to the TextViews
        txthive.setText(Hive_ID);

        TextView pollenAmount = (TextView) findViewById(R.id.pollenAmount);


        if (hive_data.getPollenAmount().equals("")) {
            pollenAmount.setText("");
        } else {
            pollenAmount.setText(hive_data.getPollenAmount());
        }


        TextView honeyAmount = (TextView) findViewById(R.id.honeyAmount);


        if (hive_data.getAmountOfHoney().equals(null)) {
            honeyAmount.setText("");
        } else {
            honeyAmount.setText(hive_data.getAmountOfHoney());
        }

        TextView beeCount = (TextView) findViewById(R.id.beeCount);


        if (hive_data.getPestCount().equals(null)) {
            beeCount.setText("");
        } else {
            beeCount.setText(hive_data.getBeeCount());
        }

        TextView emergency = (TextView) findViewById(R.id.emergency);


        if (String.valueOf(hive_data.getEmergency()).equals(null)) {
            emergency.setText("");
        } else {
            emergency.setText(String.valueOf(hive_data.getEmergency()));
        }

        TextView swarm = (TextView) findViewById(R.id.swarm);


        if (String.valueOf(hive_data.getSwarm()).equals(null)) {
            swarm.setText("");
        } else {
            swarm.setText(String.valueOf(hive_data.getSwarm()));
        }

        TextView TemperOfBees = (TextView) findViewById(R.id.TemperOfBees);

        if (String.valueOf(hive_data.getTemper()).equals(null)) {
            TemperOfBees.setText("");
        } else {
            TemperOfBees.setText(String.valueOf(hive_data.getTemper()));
        }


        TextView ColonyStrength = (TextView) findViewById(R.id.ColonyStrength);

        if (String.valueOf(hive_data.getColonyCondition()).equals(null)) {
            ColonyStrength.setText("");
        } else {
            ColonyStrength.setText(String.valueOf(hive_data.getColonyCondition()));
        }


        TextView ActionsNotes = (TextView) findViewById(R.id.ActionsNotes);

        if (String.valueOf(hive_data.getActionsAndNotes()).equals(null)) {
            ActionsNotes.setText("");
        } else {
            ActionsNotes.setText(String.valueOf(hive_data.getActionsAndNotes()));
        }


    }
}
