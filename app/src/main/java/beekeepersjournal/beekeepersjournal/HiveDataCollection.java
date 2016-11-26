package beekeepersjournal.beekeepersjournal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;

public class HiveDataCollection extends AppCompatActivity {

    public static final List<SliceValue> values = new ArrayList<SliceValue>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseQuery<Hive_Data> query = ParseQuery.getQuery(Hive_Data.class);

        query.whereNotEqualTo(GeneralObservationsActivity.framesWithHoneyOrNectar, 0);
        query.findInBackground(new FindCallback<Hive_Data>() {
            public void done(List<Hive_Data> hiveDataList, ParseException exception) {
                for (Hive_Data i : hiveDataList) {
                    if (exception == null) {
                        int framesWithHoney = i.getFramesWithHoney();
                        values.add(0, new SliceValue(framesWithHoney, ChartUtils.pickColor()));

                    } else {
                        Log.d("Frames with Honey", "Error: " + exception.getMessage());
                    }
                }
            }
        });
        };
    }
