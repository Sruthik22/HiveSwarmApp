package hiveswarm.hiveswarm;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentalInformation extends AppCompatActivity {
    // Declare Variables
    public static String ip_address;
    public static String objectId;
    List<environmental> ob;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    public static environmental environmental_data;

    public static List<String> values = new ArrayList<String>();

    public static int Query_Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_information);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        // Get the result of rank

        ip_address = i.getStringExtra("pi_ip");
        Query_Value = i.getIntExtra("List Item", 0);

        ParseQuery<environmental> query = ParseQuery.getQuery(environmental.class);
        query.whereEqualTo("ip_address", ip_address);

        try {
            ob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        environmental_data = ob.get(Query_Value);

        TextView pollenAmount = (TextView) findViewById(R.id.pollenAmount);


        pollenAmount.setText(Integer.toString(environmental_data.getPM10()));

        TextView temperature = (TextView) findViewById(R.id.Temperature);


        temperature.setText(Integer.toString(environmental_data.getTemperature()));

        TextView humidity = (TextView) findViewById(R.id.Humidity);


        humidity.setText(Integer.toString(environmental_data.getHumidity()));



    }
}
