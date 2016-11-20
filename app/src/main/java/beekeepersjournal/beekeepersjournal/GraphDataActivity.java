package beekeepersjournal.beekeepersjournal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphDataActivity extends AppCompatActivity {

    private PieChartView chart;
    private PieChartData data;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;
    private static final int CONTENT_VIEW_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_data);

        FrameLayout frame = new FrameLayout(this);
        setContentView(frame, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            Fragment newFragment = new PlaceholderFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(android.R.id.content, newFragment).commit();
        }
    }

    /**
     * A fragment containing a pie chart.
     */
    public static class PlaceholderFragment extends Fragment {

        private PieChartView chart;
        private PieChartData data;

        private boolean hasLabels = false;
        private boolean hasLabelsOutside = false;
        private boolean hasCenterCircle = false;
        private boolean hasCenterText1 = false;
        private boolean hasCenterText2 = false;
        private boolean isExploded = false;
        private boolean hasLabelForSelected = false;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

            chart = (PieChartView) rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());

            generateData();

            return rootView;
        }

        private void reset() {
            chart.setCircleFillRatio(1.0f);
            hasLabels = false;
            hasLabelsOutside = false;
            hasCenterCircle = false;
            hasCenterText1 = false;
            hasCenterText2 = false;
            isExploded = false;
            hasLabelForSelected = false;
        }

        private void generateData() {
            int numValues = 6;

            List<SliceValue> values = new ArrayList<SliceValue>();
            for (int i = 0; i < numValues; ++i) {
                SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
                values.add(sliceValue);
            }

            data = new PieChartData(values);
            data.setHasLabels(hasLabels);
            data.setHasLabelsOnlyForSelected(hasLabelForSelected);
            data.setHasLabelsOutside(hasLabelsOutside);
            data.setHasCenterCircle(hasCenterCircle);

            if (isExploded) {
                data.setSlicesSpacing(24);
            }

            if (hasCenterText1) {
                data.setCenterText1("Hello!");

                // Get roboto-italic font.
                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
                data.setCenterText1Typeface(tf);

                // Get font size from dimens.xml and convert it to sp(library uses sp values).
                data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));
            }

            if (hasCenterText2) {
                data.setCenterText2("Charts (Roboto Italic)");

                Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");

                data.setCenterText2Typeface(tf);
                data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                        (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));
            }

            chart.setPieChartData(data);
        }

        private void explodeChart() {
            isExploded = !isExploded;
            generateData();

        }

        private class ValueTouchListener implements PieChartOnValueSelectListener {

            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {
                // TODO Auto-generated method stub

            }

        }
    }
}
