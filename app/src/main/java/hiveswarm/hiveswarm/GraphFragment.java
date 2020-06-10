package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.BubbleChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.BubbleChartData;
import lecho.lib.hellocharts.model.BubbleValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.BubbleChartView;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphFragment extends Fragment {

    /* This is a fragment which appears after the user has clicked a set of buttons on which type of chart they would like to view. After the
    * user is done viewing this chart, they can press a back button, which will allow them to select new buttons to view a different chart*/

    ProgressDialog mProgressDialog;
    public static List<hive_data> ob;

    private List<BubbleValue> bubbleValues = new ArrayList<>();
    private List<Column> columns = new ArrayList<>();

    private List<Date> dateList = new ArrayList<>();

    //The Charts
    private BubbleChartView bubbleChartView;
    private ColumnChartView columnChartView;
    private PieChartView pieChartView;

    private BubbleChartData bubbleChartData;
    private ColumnChartData columnChartData;
    private PieChartData pieChartData;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private int CIRCLE_SIZE = 1;

    Bundle extras;
    String graph_to_display;
    String hive_id;
    ArrayList<String> true_check_boxes;
    ArrayList<Integer> values_of_keys;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);



        //This will tell which graph to display
        graph_to_display = extras.getString("button_pressed");
        //This will tell you which data to use in graph
        true_check_boxes = extras.getStringArrayList("true_check_boxes");
        //This will tell you the hive_id to check
        hive_id = extras.getString("hive_id");

        switch (graph_to_display) {
            case "bubble_graph":
                bubbleChartView = (BubbleChartView) rootView.findViewById(R.id.chart);
                bubbleChartView.setOnValueTouchListener(new ValueTouchListener());
                break;
            case "bar_graph":
                columnChartView = (ColumnChartView) rootView.findViewById(R.id.bar_graph);
                break;
            case "pie_chart":
                pieChartView = (PieChartView) rootView.findViewById(R.id.pie_chart);
                break;
        }

        Button choose_another_graph = (Button) rootView.findViewById(R.id.choose_another_graph);

        choose_another_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().
                        remove(GraphFragment.this).commit();
            }
        });

        new GatherGraphData().execute();



        return rootView;
    }


    private class GatherGraphData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("Getting Environmental Data");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
                query.whereEqualTo("Username", SignInActivity.sign_email);
                query.whereEqualTo(GeneralObservationsActivity.hiveId, hive_id);
                query.orderByAscending("createdAt");
                ob = query.find();

                for (hive_data i : ob) {
                    for (String checked_boxes: true_check_boxes) {
                        int get_value_of_key = i.getInt(checked_boxes);
                        values_of_keys.add(get_value_of_key);
                        values_of_keys.size();
                    }


                    Date dateOfCreation = i.getCreatedAt();

                    switch (graph_to_display) {
                        case "bubble_graph":
                            CIRCLE_SIZE = values_of_keys.get(2);
                            BubbleValue value = new BubbleValue(values_of_keys.get(0), values_of_keys.get(1), CIRCLE_SIZE);
                            value.setColor(ChartUtils.pickColor());
                            value.setShape(ValueShape.CIRCLE);
                            bubbleValues.add(0, value);
                            break;
                        case "bar_graph":
                            List<SubcolumnValue> subcolumnValues = new ArrayList<>();
                            SubcolumnValue subcolumnValue = new SubcolumnValue(values_of_keys.get(0), ChartUtils.pickColor());
                            subcolumnValues.add(subcolumnValue);
                            Column column = new Column(subcolumnValues);
                            column.setHasLabels(hasLabels);
                            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
                            columns.add(column);
                            break;
                        case "pie_chart":

                            break;
                    }


                    dateList.add(0, dateOfCreation);

                }

            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            generateData();
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


    private void generateData() {

        switch (graph_to_display) {
            case "bubble_graph":
                bubbleChartData = new BubbleChartData(bubbleValues);
                bubbleChartData.setHasLabels(hasLabels);
                bubbleChartData.setHasLabelsOnlyForSelected(hasLabelForSelected);

                if (hasAxes) {
                    Axis axisX = new Axis();
                    Axis axisY = new Axis().setHasLines(true);
                    if (hasAxesNames) {
                        axisX.setName("Axis X");
                        axisY.setName("Axis Y");
                    }
                    bubbleChartData.setAxisXBottom(axisX);
                    bubbleChartData.setAxisYLeft(axisY);
                } else {
                    bubbleChartData.setAxisXBottom(null);
                    bubbleChartData.setAxisYLeft(null);
                }

                bubbleChartView.setBubbleChartData(bubbleChartData);
                break;
            case "bar_graph":
                columnChartData = new ColumnChartData(columns);

                if (hasAxes) {
                    Axis axisX = new Axis();
                    Axis axisY = new Axis().setHasLines(true);
                    if (hasAxesNames) {
                        axisX.setName("Axis X");
                        axisY.setName("Axis Y");
                    }
                    columnChartData.setAxisXBottom(axisX);
                    columnChartData.setAxisYLeft(axisY);
                } else {
                    columnChartData.setAxisXBottom(null);
                    columnChartData.setAxisYLeft(null);
                }

                columnChartView.setColumnChartData(columnChartData);
                break;
            case "pie_chart":

                break;
        }




    }

    private class ValueTouchListener implements BubbleChartOnValueSelectListener {

        @Override
        public void onValueSelected(int bubbleIndex, BubbleValue value) {
            Toast.makeText(getActivity(), dateList.get(bubbleIndex).toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {}
    }
}
