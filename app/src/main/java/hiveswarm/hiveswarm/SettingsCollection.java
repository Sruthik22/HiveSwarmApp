package hiveswarm.hiveswarm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SettingsCollection {

    public static Context mContext;

    public static List<String> settingsArray = new ArrayList<String>();

    public SettingsCollection(Context mContext) {
        this.mContext = mContext;
        try {
            getValuesSettings();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int settingsHiveNumber;
    public int settingsFrameNumber;
    public int settingsHiveBodyNumber;
    public int settingsSuperNumber;
    public int settingsYearsOfBeekeeping;
    public String settingsLocation;
    public Date creationDate;


    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static void doStuff(String displayName, FindCallback<settings> callback) {

        ParseQuery<settings> query = ParseQuery.getQuery(settings.class);
        query.whereEqualTo("Username", displayName);
        query.orderByDescending("createdAt").setLimit(1);

        if (!isNetworkAvailable()) {
            query.fromLocalDatastore();
        }


        query.findInBackground(callback);
    }

    private void setupList(View mView, List<settings> settings) {
        Log.e("SETTINGS", String.valueOf(settings));
        settings i = settings.get(0);

        creationDate = i.getCreatedAt();
        settingsFrameNumber = i.getFramesPerHive();
        settingsArray.add(0, String.valueOf(settingsFrameNumber));

        settingsHiveNumber = i.getHiveNumber();
        settingsArray.add(1, String.valueOf(settingsHiveNumber));

        settingsHiveBodyNumber = i.getHiveBodies();
        settingsArray.add(2, String.valueOf(settingsHiveBodyNumber));

        settingsSuperNumber = i.getSupers();
        settingsArray.add(3, String.valueOf(settingsSuperNumber));

        settingsYearsOfBeekeeping = i.getYearsofBeekeeping();
        settingsArray.add(4, String.valueOf(settingsYearsOfBeekeeping));

        settingsLocation = i.getLocation();
        settingsArray.add(5, settingsLocation);

        setLablesTo(mView, settingsArray.get(0), settingsArray.get(1), settingsArray.get(2), settingsArray.get(3), settingsArray.get(4), settingsArray.get(5));
    }

    public List<String> getValuesSettings() throws ParseException {
        SettingsCollection.doStuff(SignInActivity.Display_Name, new FindCallback<settings>() {
            public void done(List<settings> settingsList, ParseException exception) {
                if (exception == null) {
                    setupList(PreferencesView.mView, settingsList);
                }
            }
        });

            /*
            query.whereNotEqualTo("createdDate", creationDate);

            for (settings settingsToDelete : query.find()) {
                settingsToDelete.deleteInBackground();
                PreferencesView.SettingsData.pinInBackground();
            }*/

        return settingsArray;
    }

    private List<String> myMethod(List<String> list) {
        return list;
    }

    public void setTextTo(View mView, int viewToFind, String text) {
        TextView textView = (TextView) mView.findViewById(viewToFind);
        textView.setText(text);
    }


    public void setLablesTo(View mView, String settingsFrameNumber, String settingsHiveNumber, String settingsHiveBodyNumber, String settingsSuperNumber, String settingsYearsOfBeekeeping, String settingsLocation) {
        setTextTo(mView, R.id.LocationTextView, settingsLocation);
        setTextTo(mView, R.id.NumberOfHivesTextView, settingsHiveNumber);
        setTextTo(mView, R.id.FramesPerHiveTextView, settingsFrameNumber);
        setTextTo(mView, R.id.HiveBodiesTextView, settingsHiveBodyNumber);
        setTextTo(mView, R.id.beekeeping_length, settingsYearsOfBeekeeping);
        setTextTo(mView, R.id.SupersTextView, settingsSuperNumber);
    }


}
