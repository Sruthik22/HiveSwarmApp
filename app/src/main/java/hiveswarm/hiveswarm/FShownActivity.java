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

public class FShownActivity extends AppCompatActivity {

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
                        Intent queen_intent = new Intent();
                        queen_intent.setClass(getApplicationContext(), EQShownActivity.class);
                        startActivity(queen_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.eat:
                        Toast.makeText(getApplicationContext(),"You are already on this Page!",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_fshown);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        TextView NumberOfFramesWithFoundation = (TextView) findViewById(R.id.NumberOfFramesWithFoundation);

        if (String.valueOf(HiveInformation.hive_data.getFramesOfFoundation()).equals(null)) {
            NumberOfFramesWithFoundation.setText("");
            HiveInformation.values.add("");
        } else {
            NumberOfFramesWithFoundation.setText(String.valueOf(HiveInformation.hive_data.getFramesOfFoundation()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesOfFoundation()));
        }


        TextView NumberofFrameswithHoneyorNectar = (TextView) findViewById(R.id.NumberofFrameswithHoneyorNectar);

        if (String.valueOf(HiveInformation.hive_data.getFramesWithHoney()).equals(null)) {
            NumberofFrameswithHoneyorNectar.setText("");
            HiveInformation.values.add("");
        } else {
            NumberofFrameswithHoneyorNectar.setText(String.valueOf(HiveInformation.hive_data.getFramesWithHoney()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesWithHoney()));
        }

        TextView NumberofFrameswithPollen = (TextView) findViewById(R.id.NumberofFrameswithPollen);

        if (String.valueOf(HiveInformation.hive_data.getFramesOfPollen()).equals(null)) {
            NumberofFrameswithPollen.setText("");
            HiveInformation.values.add("");
        } else {
            NumberofFrameswithPollen.setText(String.valueOf(HiveInformation.hive_data.getFramesOfPollen()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesOfPollen()));
        }


        TextView NumberofFrameswithOpenComb = (TextView) findViewById(R.id.NumberofFrameswithOpenComb);
        NumberofFrameswithOpenComb.setText(String.valueOf(HiveInformation.hive_data.getFramesOpenComb()));

        if (String.valueOf(HiveInformation.hive_data.getFramesOpenComb()).equals(null)) {
            NumberofFrameswithOpenComb.setText("");
            HiveInformation.values.add("");
        } else {
            NumberofFrameswithOpenComb.setText(String.valueOf(HiveInformation.hive_data.getFramesOpenComb()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getFramesOpenComb()));
        }
    }
}
