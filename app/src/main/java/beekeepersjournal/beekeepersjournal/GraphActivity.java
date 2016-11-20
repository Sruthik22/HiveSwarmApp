package beekeepersjournal.beekeepersjournal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        PieChartView chart = new PieChartView(this.getApplicationContext());

        ParseQuery<Hive_Data> query = ParseQuery.getQuery(Hive_Data.class);

        ParseObject hive_data = new ParseObject("Hive_Data");


        List<SliceValue> values = new ArrayList<SliceValue>();
        try {
            for (int i = 0; i < query.count(); i++) {
                query.get(hive_data.getObjectId());
            }
        }

        catch (Exception e) {

        }
        SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
        values.add(sliceValue);
    }
}
