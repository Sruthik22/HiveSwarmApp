package hiveswarm.hiveswarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphFragment extends Fragment {


    private List<SliceValue> values;
    private List<String> dates;

    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;

    public PieChartView chart;
    public PieChartData data;

    private HiveDataCollection hiveDataCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        chart = (PieChartView) rootView.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new GraphFragment.ValueTouchListener());

        TextView displayAddData = (TextView) rootView.findViewById(R.id.displayAddData);

        generateData();

        return rootView;
    }


    private void generateData() {

        hiveDataCollection = new HiveDataCollection(getContext());

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                values = hiveDataCollection.framesOfHoney;
                data = new PieChartData(values);
                data.setHasLabels(true);
                data.setHasLabelsOnlyForSelected(true);
                data.setHasLabelsOutside(true);
                data.setHasCenterCircle(true);

                data.setCenterText1("Frames With Honey");

                data.setCenterText2("Each Record Saved");

                data.setCenterText1FontSize(20);

                chart.setPieChartData(data);

                dates = hiveDataCollection.dates;
            }
        };

        getActivity().getApplicationContext().registerReceiver(receiver, new IntentFilter("com.beekeepersjournal.android.USER_ACTION"));


    }

    public class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {

            String dateOfCreation = (dates.get(arcIndex));

            Toast.makeText(getActivity(), dateOfCreation, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
