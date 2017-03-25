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

public class PShownActivity extends AppCompatActivity {

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
                        Intent environment_intent = new Intent();
                        environment_intent.setClass(getApplicationContext(), EShownActivity.class);
                        startActivity(environment_intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pest:
                        Toast.makeText(getApplicationContext(),"You are already on this Page!",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_pshown);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        TextView VarroaMites = (TextView) findViewById(R.id.VarroaMites);

        if (String.valueOf(HiveInformation.hive_data.getAreThereVarroaMites()).equals(null)) {
            VarroaMites.setText("");
            HiveInformation.values.add("");
        } else {
            VarroaMites.setText(String.valueOf(HiveInformation.hive_data.getAreThereVarroaMites()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getAreThereVarroaMites()));
        }

        TextView HiveBeetles = (TextView) findViewById(R.id.HiveBeetles);

        if (String.valueOf(HiveInformation.hive_data.getAreThereHiveBeetles()).equals(null)) {
            HiveBeetles.setText("");
            HiveInformation.values.add("");
        } else {
            HiveBeetles.setText(String.valueOf(HiveInformation.hive_data.getAreThereHiveBeetles()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getAreThereHiveBeetles()));
        }

        TextView HiveBeetlesAmount = (TextView) findViewById(R.id.HiveBeetlesAmount);

        if (String.valueOf(HiveInformation.hive_data.getAmountOfHiveBeetles()).equals(null)) {
            HiveBeetlesAmount.setText("");
            HiveInformation.values.add("");
        } else {
            HiveBeetlesAmount.setText(String.valueOf(HiveInformation.hive_data.getAmountOfHiveBeetles()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getAmountOfHiveBeetles()));
        }

        TextView HiveBeetlesAffect = (TextView) findViewById(R.id.HiveBeetlesAffect);

        if (String.valueOf(HiveInformation.hive_data.getHiveBeetleAffect()).equals(null)) {
            HiveBeetlesAffect.setText("");
            HiveInformation.values.add("");
        } else {
            HiveBeetlesAffect.setText(String.valueOf(HiveInformation.hive_data.getHiveBeetleAffect()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getHiveBeetleAffect()));
        }

        TextView VarroaMitesAmount = (TextView) findViewById(R.id.VarroaMitesAmount);

        if (String.valueOf(HiveInformation.hive_data.getAmountOfVarroaMite()).equals(null)) {
            VarroaMitesAmount.setText("");
            HiveInformation.values.add("");
        } else {
            VarroaMitesAmount.setText(String.valueOf(HiveInformation.hive_data.getAmountOfVarroaMite()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getAmountOfVarroaMite()));
        }

        TextView VarroaMitesAffect = (TextView) findViewById(R.id.VarroaMitesAffect);

        if (String.valueOf(HiveInformation.hive_data.getVarroaMiteAffect()).equals(null)) {
            VarroaMitesAffect.setText("");
            HiveInformation.values.add("");
        } else {
            VarroaMitesAffect.setText(String.valueOf(HiveInformation.hive_data.getVarroaMiteAffect()));
            HiveInformation.values.add(String.valueOf(HiveInformation.hive_data.getVarroaMiteAffect()));
        }
    }
}
