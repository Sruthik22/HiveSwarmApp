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

import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;

public class HiveDataCollection {

    public static Context mContext;
    public GraphFragment fragment;


    public HiveDataCollection(Context mContext) {
        this.mContext = mContext;
        getValues();
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public List<SliceValue> framesOfHoney = new ArrayList<SliceValue>();
    public List<String> dates = new ArrayList<String>();

    public static void doStuff(String displayName, FindCallback<hive_data> callback) {

        ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
        query.whereGreaterThan(GeneralObservationsActivity.framesWithHoneyOrNectar, 0);
        query.whereEqualTo("Username", displayName);

        if (!isNetworkAvailable()) {
            query.fromLocalDatastore();
        }


        query.findInBackground(callback);
    }

    private void setupList(List<hive_data> hiveDataList) {

        framesOfHoney.clear();

        for (hive_data i : hiveDataList) {
            int framesWithHoney = i.getFramesWithHoney();
            Date dateOfCreation = i.getCreatedAt();
            framesOfHoney.add(0, new SliceValue(framesWithHoney, ChartUtils.pickColor()));
            dates.add(dateOfCreation.toString());
        }


        Log.e("framesOfHoney", framesOfHoney.toString());
        Intent intent = new Intent("com.beekeepersjournal.android.USER_ACTION");
        mContext.sendBroadcast(intent);


    }

    public void getValues() {
        HiveDataCollection.doStuff(SignInActivity.Display_Name, new FindCallback<hive_data>() {
            public void done(List<hive_data> hiveDataList, ParseException exception) {
                if (exception == null && !hiveDataList.isEmpty()) {
                    setupList(hiveDataList);
                }
            }
        });
    }
}
