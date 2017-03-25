package hiveswarm.hiveswarm;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;

public class HiveDataCollection {

    public static Context mContext;
    public GraphFragment fragment;


    public HiveDataCollection(Context mContext) {
        this.mContext = mContext;
        getValues();
    }

    public List<PointValue> framesOfHoney = new ArrayList<PointValue>();
    public List<String> dates = new ArrayList<String>();

    public static void doStuff(String displayName, FindCallback<environmental> callback) {

        ParseQuery<environmental> query = ParseQuery.getQuery(environmental.class);
        query.whereGreaterThan(GeneralObservationsActivity.framesWithHoneyOrNectar, 0);
        query.whereEqualTo("Username", displayName);

        query.findInBackground(callback);
    }

    private void setupList(List<environmental> hiveDataList) {

        framesOfHoney.clear();

        for (environmental i : hiveDataList) {
            int temperature = i.getTemperature();
            int humidity = i.getHumidity();
            Date dateOfCreation = i.getCreatedAt();
            framesOfHoney.add(0, new PointValue(temperature, ChartUtils.pickColor()));
            dates.add(dateOfCreation.toString());
        }


        Log.e("framesOfHoney", framesOfHoney.toString());
        Intent intent = new Intent("com.beekeepersjournal.android.USER_ACTION");
        mContext.sendBroadcast(intent);


    }

    public void getValues() {
        HiveDataCollection.doStuff(SignInActivity.sign_email, new FindCallback<environmental>() {
            public void done(List<environmental> hiveDataList, ParseException exception) {
                if (exception == null && !hiveDataList.isEmpty()) {
                    setupList(hiveDataList);
                }
            }
        });
    }
}
