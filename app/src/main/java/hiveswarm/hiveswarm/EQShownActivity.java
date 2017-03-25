package hiveswarm.hiveswarm;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EQShownActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see



    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        Intent home_intent = new Intent();
                        home_intent.setClass(getApplicationContext(), HiveInformation.class);
                        startActivity(home_intent);
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
                        Toast.makeText(getApplicationContext(),"You are already on this Page!",Toast.LENGTH_SHORT).show();
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
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText(SignInActivity.sign_email);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eqshown);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        TextView QueenSeen = (TextView) findViewById(R.id.QueenSeen);

        if (String.valueOf(HiveInformation.hive_data.getQueenSeen()).equals(null)) {
            QueenSeen.setText("");
            HiveInformation.values.add("");
        } else {
            QueenSeen.setText(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
        }

        TextView QueenMarked = (TextView) findViewById(R.id.QueenMarked);

        if (String.valueOf(HiveInformation.hive_data.getQueenSeen()).equals(null)) {
            QueenSeen.setText("");
            HiveInformation.values.add("");
        } else {
            QueenSeen.setText(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
        }

        TextView QueenEggsSeen = (TextView) findViewById(R.id.QueenEggsSeen);

        if (String.valueOf(HiveInformation.hive_data.getQueenSeen()).equals(null)) {
            QueenEggsSeen.setText("");
            HiveInformation.values.add("");
        } else {
            QueenEggsSeen.setText(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getQueenSeen()));
        }

        TextView EggLarvaPupaSeen = (TextView) findViewById(R.id.EggLarvaPupaSeen);

        if (String.valueOf(HiveInformation.hive_data.getEggLarvaOrPupaSeen()).equals(null)) {
            EggLarvaPupaSeen.setText("");
            HiveInformation.values.add("");
        } else {
            EggLarvaPupaSeen.setText(String.valueOf(HiveInformation.hive_data.getEggLarvaOrPupaSeen()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getEggLarvaOrPupaSeen()));
        }

        TextView RemovedQueenCells = (TextView) findViewById(R.id.RemovedQueenCells);

        if (String.valueOf(HiveInformation.hive_data.getRemovedQueenCells()).equals(null)) {
            RemovedQueenCells.setText("");
            HiveInformation.values.add("");
        } else {
            RemovedQueenCells.setText(String.valueOf(HiveInformation.hive_data.getRemovedQueenCells()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getRemovedQueenCells()));
        }

        TextView remainQueenCells = (TextView) findViewById(R.id.remainQueenCells);

        if (String.valueOf(HiveInformation.hive_data.getQueenCellsRemaining()).equals(null)) {
            remainQueenCells.setText("");
            HiveInformation.values.add("");
        } else {
            remainQueenCells.setText(String.valueOf(HiveInformation.hive_data.getQueenCellsRemaining()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getQueenCellsRemaining()));
        }

        TextView supersedure = (TextView) findViewById(R.id.supersedure);

        if (String.valueOf(HiveInformation.hive_data.getSupersedure()).equals(null)) {
            supersedure.setText("");
            HiveInformation.values.add("");
        } else {
            supersedure.setText(String.valueOf(HiveInformation.hive_data.getSupersedure()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getSupersedure()));
        }

        TextView SpottyDroneBrood = (TextView) findViewById(R.id.SpottyDroneBrood);

        if (String.valueOf(HiveInformation.hive_data.getSpottyDroneBrood()).equals(null)) {
            SpottyDroneBrood.setText("");
            HiveInformation.values.add("");
        } else {
            SpottyDroneBrood.setText(String.valueOf(HiveInformation.hive_data.getSpottyDroneBrood()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getSpottyDroneBrood()));
        }

        TextView FramesWithBrood = (TextView) findViewById(R.id.FramesWithBrood);

        if (String.valueOf(HiveInformation.hive_data.getFramesWithBrood()).equals(null)) {
            FramesWithBrood.setText("");
            HiveInformation.values.add("");
        } else {
            FramesWithBrood.setText(String.valueOf(HiveInformation.hive_data.getFramesWithBrood()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesWithBrood()));
        }

        TextView BroodInAllStages = (TextView) findViewById(R.id.BroodInAllStages);

        if (String.valueOf(HiveInformation.hive_data.getBroodInAllStages()).equals(null)) {
            BroodInAllStages.setText("");
            HiveInformation.values.add("");
        } else {
            BroodInAllStages.setText(String.valueOf(HiveInformation.hive_data.getBroodInAllStages()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getBroodInAllStages()));
        }

        TextView BroodPattern = (TextView) findViewById(R.id.BroodPattern);

        if (String.valueOf(HiveInformation.hive_data.getCompactBroodPattern()).equals(null)) {
            BroodPattern.setText("");
            HiveInformation.values.add("");
        } else {
            BroodPattern.setText(String.valueOf(HiveInformation.hive_data.getCompactBroodPattern()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getCompactBroodPattern()));
        }

        TextView FramesUsedInBroodChamber = (TextView) findViewById(R.id.FramesUsedInBroodChamber);

        if (String.valueOf(HiveInformation.hive_data.getFramesWithBrood()).equals(null)) {
            FramesUsedInBroodChamber.setText("");
            HiveInformation.values.add("");
        } else {
            FramesUsedInBroodChamber.setText(String.valueOf(HiveInformation.hive_data.getFramesWithBrood()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesWithBrood()));
        }
    }
}
