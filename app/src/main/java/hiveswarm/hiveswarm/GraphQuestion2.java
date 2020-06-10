package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GraphQuestion2 extends Fragment {

    /* In this class we will ask the user, which fields of data they will like to see. We need to ask the user the number of data fields that \
    correspond to the button pressed earlier. Then we will go to the GraphFragment so the user can visualize the data */

    private ArrayList<String> spinnerArray = new ArrayList<>();
    public static List<hive_data> ob;
    ProgressDialog mProgressDialog;
    View rootView;
    String graph_to_display;
    int number_of_selected_data_fields;
    Bundle extras;
    ArrayList<String> true_check_boxes;
    Spinner s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                getActivity();
            }
        });
        extras = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.activity_graph_question2, container, false);

        //This will tell which graph to display
        graph_to_display = extras.getString("button_pressed");
        number_of_selected_data_fields = extras.getInt("number_of_selected_data_fields");

        final CheckBox hs_amount = (CheckBox) rootView.findViewById(R.id.hs_amount);
        final CheckBox hs_frequency = (CheckBox) rootView.findViewById(R.id.hs_frequency);
        final CheckBox hs_how_many_frames_used_in_brood_chamber = (CheckBox) rootView.findViewById(R.id.hs_how_many_frames_used_in_brood_chamber);
        final CheckBox hs_how_many_frames_with_brood = (CheckBox) rootView.findViewById(R.id.hs_how_many_frames_with_brood);
        final CheckBox hs_how_many_remaining_queen_cells = (CheckBox) rootView.findViewById(R.id.hs_how_many_remaining_queen_cells);
        final CheckBox hs_humidity = (CheckBox) rootView.findViewById(R.id.hs_humidity);
        final CheckBox hs_number_of_frames_with_foundation = (CheckBox) rootView.findViewById(R.id.hs_number_of_frames_with_foundation);
        final CheckBox hs_number_of_frames_with_honey_nectar = (CheckBox) rootView.findViewById(R.id.hs_number_of_frames_with_honey_nectar);
        final CheckBox hs_number_of_frames_with_open_comb = (CheckBox) rootView.findViewById(R.id.hs_number_of_frames_with_open_comb);
        final CheckBox hs_number_of_frames_with_pollen = (CheckBox) rootView.findViewById(R.id.hs_number_of_frames_with_pollen);
        final CheckBox hs_number_of_supers_added = (CheckBox) rootView.findViewById(R.id.hs_number_of_supers_added);
        final CheckBox hs_number_of_supers_in_place = (CheckBox) rootView.findViewById(R.id.hs_number_of_supers_in_place);
        final CheckBox hs_precipitation = (CheckBox) rootView.findViewById(R.id.hs_precipitation);
        final CheckBox hs_temperature = (CheckBox) rootView.findViewById(R.id.hs_temperature);


        Button create = (Button) rootView.findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The user clicked on the create button
                //We need to check if all the data is legit ie. The spinner


                String text = s.getSelectedItem().toString();

                if (!text.equals("")) {
                    //We need to check if the user has chosen the correct number of points
                    //We will do this by seeing if each checkbox is checked or unchecked
                    ArrayList<Boolean> checked_boxes = new ArrayList<>();
                    checked_boxes.add(hs_amount.isChecked());
                    checked_boxes.add(hs_frequency.isChecked());
                    checked_boxes.add(hs_how_many_frames_used_in_brood_chamber.isChecked());
                    checked_boxes.add(hs_how_many_frames_with_brood.isChecked());
                    checked_boxes.add(hs_how_many_remaining_queen_cells.isChecked());
                    checked_boxes.add(hs_humidity.isChecked());
                    checked_boxes.add(hs_number_of_frames_with_foundation.isChecked());
                    checked_boxes.add(hs_number_of_frames_with_honey_nectar.isChecked());
                    checked_boxes.add(hs_number_of_frames_with_open_comb.isChecked());
                    checked_boxes.add(hs_number_of_frames_with_pollen.isChecked());
                    checked_boxes.add(hs_number_of_supers_added.isChecked());
                    checked_boxes.add(hs_number_of_supers_in_place.isChecked());
                    checked_boxes.add(hs_precipitation.isChecked());
                    checked_boxes.add(hs_temperature.isChecked());

                    String[] names_of_fields = {
                            Feeding.amountOfFoodFed,
                            Feeding.frequencyOfFeeding,
                            EggQueenObservations.framesUsedBroodChamber,
                            EggQueenObservations.framesWithBrood,
                            EggQueenObservations.queenCellsRemaining,
                            EnvironmentalConditions.humidity,
                            GeneralObservationsActivity.framesOfFoundation,
                            GeneralObservationsActivity.framesWithHoneyOrNectar,
                            GeneralObservationsActivity.framesOpenComb,
                            GeneralObservationsActivity.framesOfPollen,
                            GeneralObservationsActivity.supersAdded,
                            GeneralObservationsActivity.supersInPlace,
                            EnvironmentalConditions.precipitation,
                            EnvironmentalConditions.temperature
                    };

                    true_check_boxes = new ArrayList<>();

                    int true_count = 0;

                    for (int i = 0; i < checked_boxes.size(); i++) {
                        if (checked_boxes.get(i)) {
                            //As well as getting the correct count, we need to get the true fields
                            true_check_boxes.add(names_of_fields[i]);
                            true_count++;
                        }
                    }

                    if (true_count == number_of_selected_data_fields) {
                        //Everything is good, we can check which of the boxes are checked an then hand the data to the Graph Fragment
                        changeFragment();
                    } else {
                        Toast toast = Toast.makeText(getContext(), "The number of selected text boxes is: " + number_of_selected_data_fields, Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(getContext(), "Try Again!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        //This will populate the spinner with the different hive_id's
        new GetHiveId().execute();
        return rootView;
    }

    // RemoteDataTask AsyncTask
    private class GetHiveId extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getContext());
            // Set progressdialog title
            mProgressDialog.setTitle("Getting Data from DB");
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
                query.orderByAscending("createdAt");
                ob = query.find();

                ArrayList<String> already_said = new ArrayList<>();
                for (hive_data hive_data : ob) {
                    String hiveId = hive_data.getHiveId();

                    if (!already_said.contains(hiveId)) {
                        spinnerArray.add(hiveId);
                        already_said.add(hiveId);
                    }
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            s = (Spinner) rootView.findViewById(R.id.hive_id_spinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, spinnerArray);
            s.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

    public void changeFragment() {
        //Giving the new fragment the extra data needed to make a graph
        Bundle data = new Bundle();
        data.putString("button_pressed", graph_to_display);
        data.putStringArrayList("true_check_boxes", true_check_boxes);
        data.putString("hive_id", s.getSelectedItem().toString());

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GraphFragment graphFragment = new GraphFragment();

        //Give the fragments the data Bundle
        graphFragment.setArguments(data);

        //Show the new fragment to the user
        fragmentTransaction.replace(R.id.FRAGMENT_PLACEHOLDER, graphFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
