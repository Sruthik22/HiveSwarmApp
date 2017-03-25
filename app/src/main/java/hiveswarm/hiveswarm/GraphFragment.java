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

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphFragment extends Fragment {

    private List<PointValue> values;
    private List<String> dates;

    public LineChartView chart;
    public LineChartData data;

    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;

    private HiveDataCollection hiveDataCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_line_chart, container, false);

        chart = (LineChartView) rootView.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new GraphFragment.ValueTouchListener());

        generateData();

        return rootView;
    }


    private void generateData() {

        hiveDataCollection = new HiveDataCollection(getContext());

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                //values = hiveDataCollection.framesOfHoney;
                //data = new LineChartData(values);

                List<Line> lines = new ArrayList<Line>();
                for (int i = 0; i < numberOfLines; ++i) {

                    List<PointValue> values = new ArrayList<PointValue>();
                    for (int j = 0; j < numberOfPoints; ++j) {
                        values.add(new PointValue(j, randomNumbersTab[i][j]));
                    }

                    Line line = new Line(values);
                    line.setColor(ChartUtils.COLORS[i]);
                    line.setShape(shape);
                    line.setCubic(isCubic);
                    line.setFilled(isFilled);
                    line.setHasLabels(hasLabels);
                    line.setHasLabelsOnlyForSelected(hasLabelForSelected);
                    line.setHasLines(hasLines);
                    line.setHasPoints(hasPoints);
                    lines.add(line);
                }

                data = new LineChartData(lines);

                if (hasAxes) {
                    Axis axisX = new Axis();
                    Axis axisY = new Axis().setHasLines(true);
                    if (hasAxesNames) {
                        axisX.setName("Axis X");
                        axisY.setName("Axis Y");
                    }
                    data.setAxisXBottom(axisX);
                    data.setAxisYLeft(axisY);
                } else {
                    data.setAxisXBottom(null);
                    data.setAxisYLeft(null);
                }

                data.setBaseValue(Float.NEGATIVE_INFINITY);
                chart.setLineChartData(data);



                dates = hiveDataCollection.dates;
            }
        };

        getActivity().getApplicationContext().registerReceiver(receiver, new IntentFilter("com.beekeepersjournal.android.USER_ACTION"));


    }

    public class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            String dateOfCreation = (dates.get(lineIndex));

            Toast.makeText(getActivity(), dateOfCreation, Toast.LENGTH_SHORT).show();
        }
    }
}
