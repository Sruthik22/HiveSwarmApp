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

public class EShownActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

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
                        Toast.makeText(getApplicationContext(),"You are already on this Page!",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_eshown);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        TextView Temperature = (TextView) findViewById(R.id.Temperature);

        if (String.valueOf(HiveInformation.hive_data.getWeatherTemperature()).equals(null)) {
            Temperature.setText("");
            HiveInformation.values.add("");
        } else {
            Temperature.setText(String.valueOf(HiveInformation.hive_data.getWeatherTemperature()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getWeatherTemperature()));
        }


        TextView Humidity = (TextView) findViewById(R.id.Humidity);

        if (String.valueOf(HiveInformation.hive_data.getWeatherHumidity()).equals(null)) {
            Humidity.setText("");
            HiveInformation.values.add("");
        } else {
            Humidity.setText(String.valueOf(HiveInformation.hive_data.getWeatherHumidity()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getWeatherHumidity()));
        }


        TextView Precipitation = (TextView) findViewById(R.id.Precipitation);

        if (String.valueOf(HiveInformation.hive_data.getWeatherPrecipitation()).equals(null)) {
            Precipitation.setText("");
            HiveInformation.values.add("");
        } else {
            Precipitation.setText(String.valueOf(HiveInformation.hive_data.getWeatherPrecipitation()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getWeatherPrecipitation()));
        }


        TextView CloudCoverage = (TextView) findViewById(R.id.CloudCoverage);

        if (String.valueOf(HiveInformation.hive_data.getWeatherCloudiness()).equals(null)) {
            CloudCoverage.setText("");
            HiveInformation.values.add("");
        } else {
            CloudCoverage.setText(String.valueOf(HiveInformation.hive_data.getWeatherCloudiness()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getWeatherCloudiness()));
        }
    }
}
