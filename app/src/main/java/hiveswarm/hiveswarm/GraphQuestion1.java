package hiveswarm.hiveswarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GraphQuestion1 extends Fragment {

    /* In this fragment, we will ask the user what kind of graph they will like to be displayed. The choices will include Pie Chart, Bar Graph,
    Scatter Plot, And Bubble Chart. After the user selects one of these choices, they will then be directed to another page which lets them choose
    the data points They want to use on that graph

    This fragment shall also be the parent fragment*/

    String button_pressed = "";
    int number_of_data_points;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_graph_question1, container, false);

        Button pie_chart = (Button) rootView.findViewById(R.id.pie_chart);

        pie_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The user clicked on the pie chart button
                //If this is the case, we need 1 data point
                number_of_data_points = 1;
                button_pressed = "pie_chart";
                changeFragment(v);
            }
        });

        Button bar_graph = (Button) rootView.findViewById(R.id.bar_graph);

        bar_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The user clicked on the bar graph button
                //If this is the case, we need 1 data point
                number_of_data_points = 1;
                button_pressed = "bar_graph";
                changeFragment(v);
            }
        });

        Button bubble_graph = (Button) rootView.findViewById(R.id.bubble_graph);

        bubble_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The user clicked on the scatter plot button
                //If this is the case, we need 3 data points
                number_of_data_points = 3;
                button_pressed = "bubble_graph";
                changeFragment(v);
            }
        });

        return rootView;
    }

    public void changeFragment(View v) {
        Bundle data = new Bundle();
        data.putString("button_pressed", button_pressed);
        data.putInt("number_of_selected_data_fields", number_of_data_points);
        FragmentManager childFragMan = getActivity().getSupportFragmentManager();
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        GraphQuestion2 fragB = new GraphQuestion2();
        fragB.setArguments(data);
        childFragTrans.replace(R.id.FRAGMENT_PLACEHOLDER, fragB);
        childFragTrans.addToBackStack("B");
        childFragTrans.commit();
    }
}
