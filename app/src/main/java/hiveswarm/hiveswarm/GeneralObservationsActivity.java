package hiveswarm.hiveswarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseObject;

public class GeneralObservationsActivity extends Activity {

    public static final String tableName = "hive_data";
    public static final String hiveId = "Hive_ID";
    public static final String PollenAmount = "Amount_of_Pollen";
    public static final String HoneyAmount = "Amount_of_honey";
    public static final String pestCount = "Pest_count";
    public static final String beeCount = "Bee_count";
    public static final String Emergency = "Emergency";
    public static final String swarm = "Swarm";

    public static final String framesOfFoundation = "Frames_of_Foundation";
    public static final String framesWithHoneyOrNectar = "Frames_with_honey_or_nectar";
    public static final String framesOfPollen = "Frames_of_Pollen";
    public static final String framesOpenComb = "Frames_Open_Comb";
    public static final String supersInPlace = "Supers_in_place";
    public static final String supersAdded = "Supers_added";
    public static final String temper = "Temper";

    public static final String colonyCondition = "Colony_Condition";
    public static final String actionsNotes = "Actions_Took_and_Notes";
    public static final String username = "Username";
    public final static ParseObject hiveData = ParseObject.create(tableName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_observations);
        Button launch = (Button) findViewById(R.id.btnSave);
        launch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        EditText HiveID = (EditText) findViewById(R.id.HiveIDEditText);
                        String hiveID = HiveID.getText().toString();

                        if (hiveID.equals("")) {
                            //They didn't enter anything in the hive_id
                            Toast.makeText(getApplicationContext(),
                                    "Please enter a Hive_ID",
                                    Toast.LENGTH_LONG).show();
                        }

                        else {

                            hiveData.put(username, SignInActivity.sign_email);
                            hiveData.put(hiveId, hiveID);

                            //Pollen
                            RadioGroup PollenAmountRadioGroup = (RadioGroup) findViewById(R.id.PollenAmountRadioButton);
                            int PollenselectedId = PollenAmountRadioGroup.getCheckedRadioButtonId();

                            if (PollenselectedId != -1) {
                                String PollenRadioButtonText = ((RadioButton) findViewById(PollenAmountRadioGroup.getCheckedRadioButtonId())).getText().toString();

                                hiveData.put(PollenAmount, PollenRadioButtonText);
                            } else {
                                hiveData.put(PollenAmount, "");
                            }

                            //Honey Amount
                            RadioGroup HoneyAmountRadioGroup = (RadioGroup) findViewById(R.id.HoneyAmountRadioButton);

                            RadioButton HoneyRadioButton = (RadioButton) findViewById(HoneyAmountRadioGroup.getCheckedRadioButtonId());
                            if (HoneyRadioButton != null) {

                                hiveData.put(HoneyAmount, HoneyRadioButton.getText().toString());

                            } else {
                                hiveData.put(HoneyAmount, "");
                            }

                            //PestCount
                            RadioGroup PestCountRadioGroup = (RadioGroup) findViewById(R.id.PestCountRadioButton);

                            RadioButton PestCountRadioButton = (RadioButton) findViewById(PestCountRadioGroup.getCheckedRadioButtonId());
                            if (PestCountRadioButton != null) {

                                hiveData.put(pestCount, PestCountRadioButton.getText().toString());
                            } else {
                                hiveData.put(pestCount, "");
                            }

                            //BeeCount
                            RadioGroup BeeCountRadioGroup = (RadioGroup) findViewById(R.id.BeeCountRadioButton);
                            RadioButton BeeCountRadioButton;
                            int BeeCountselectedID = BeeCountRadioGroup.getCheckedRadioButtonId();

                            if (BeeCountselectedID != -1) {
                                BeeCountRadioButton = (RadioButton) findViewById(BeeCountselectedID);
                                hiveData.put(beeCount, BeeCountRadioButton.getText().toString());
                            } else {
                                hiveData.put(beeCount, "");
                            }

                            //Emergency
                            RadioGroup EmergencyRadioGroup = (RadioGroup) findViewById(R.id.Emergency);
                            RadioButton EmergencyRadioButton;
                            int emergencyselectedID = EmergencyRadioGroup.getCheckedRadioButtonId();
                            if (emergencyselectedID != -1) {
                                EmergencyRadioButton = (RadioButton) findViewById(emergencyselectedID);
                                boolean emergency;

                                if (EmergencyRadioButton.getText().toString().equals("Yes")) {
                                    emergency = true;
                                } else {
                                    emergency = false;
                                }

                                hiveData.put(Emergency, emergency);
                            } else {
                                hiveData.put(Emergency, false);
                            }

                            //Swarm
                            RadioGroup SwarmRadioGroup = (RadioGroup) findViewById(R.id.Swarm);
                            RadioButton SwarmRadioButton;
                            int swarmselectedID = SwarmRadioGroup.getCheckedRadioButtonId();
                            if (swarmselectedID != -1) {
                                SwarmRadioButton = (RadioButton) findViewById(swarmselectedID);
                                boolean swarmValue;

                                if (SwarmRadioButton.getText().toString().equals("Yes")) {
                                    swarmValue = true;
                                } else {
                                    swarmValue = false;
                                }

                                hiveData.put(swarm, swarmValue);
                            } else {
                                hiveData.put(swarm, false);
                            }


                            //Frames of Foundation
                            EditText FramesOfFoundation = (EditText) findViewById(R.id.FramesWithFoundation);
                            int NumberofFramesOfFoundation = 0;
                            if (!FramesOfFoundation.getText().toString().equals("")) {
                                NumberofFramesOfFoundation = Integer.parseInt(FramesOfFoundation.getText().toString());
                            }
                            hiveData.put(framesOfFoundation, NumberofFramesOfFoundation);

                            //Frames with honey or nectar
                            EditText FramesWithHoneyorNectar = (EditText) findViewById(R.id.FramesWithHoneyOrNectar);
                            int NumberOfFramesWithHoneyOrNectar = 0;
                            if (!FramesWithHoneyorNectar.getText().toString().equals("")) {
                                NumberOfFramesWithHoneyOrNectar = Integer.parseInt(FramesWithHoneyorNectar.getText().toString());
                            }
                            hiveData.put(framesWithHoneyOrNectar, NumberOfFramesWithHoneyOrNectar);

                            //Frames Of Pollen
                            EditText FramesofPollen = (EditText) findViewById(R.id.FramesWithPollen);
                            int NumberOfFramesWithPollen = 0;
                            if (!FramesofPollen.getText().toString().equals("")) {
                                NumberOfFramesWithPollen = Integer.parseInt(FramesofPollen.getText().toString());
                            }
                            hiveData.put(framesOfPollen, NumberOfFramesWithPollen);

                            //Frames with Open Comb
                            EditText FramesofOpenComb = (EditText) findViewById(R.id.FramesWithOpenComb);
                            int NumberOfFramesWithOpenComb = 0;
                            if (!FramesofOpenComb.getText().toString().equals("")) {
                                NumberOfFramesWithOpenComb = Integer.parseInt(FramesofOpenComb.getText().toString());
                            }
                            hiveData.put(framesOpenComb, NumberOfFramesWithOpenComb);

                            //Supers In Place
                            EditText SupersInPlace = (EditText) findViewById(R.id.FramesWithSupersInPlace);
                            int NumberOfFramesWithSupersInPlace = 0;
                            if (!SupersInPlace.getText().toString().equals("")) {
                                Integer.parseInt(SupersInPlace.getText().toString());
                            }
                            hiveData.put(supersInPlace, NumberOfFramesWithSupersInPlace);

                            //Supers Added
                            EditText SupersAdded = (EditText) findViewById(R.id.SupersAdded);
                            int NumberOfSupersAdded = 0;
                            if (!SupersAdded.getText().toString().equals("")) {
                                Integer.parseInt(SupersAdded.getText().toString());
                            }
                            hiveData.put(supersAdded, NumberOfSupersAdded);


                            //Temper
                            RadioGroup TemperRadioGroup = (RadioGroup) findViewById(R.id.TemperRadioGroup);
                            RadioButton TemperRadioButton;
                            int TemperselectedID = TemperRadioGroup.getCheckedRadioButtonId();

                            if (TemperselectedID != -1) {
                                TemperRadioButton = (RadioButton) findViewById(TemperselectedID);
                                hiveData.put(temper, TemperRadioButton.getText().toString());
                            } else {
                                hiveData.put(temper, "");
                            }

                            //Colony Condition
                            RadioGroup ColonyConditionRadioGroup = (RadioGroup) findViewById(R.id.ColonyConditionRadioGroup);
                            RadioButton ColonyConditionRadioButton;
                            int ColonyConditionselectedID = ColonyConditionRadioGroup.getCheckedRadioButtonId();

                            if (ColonyConditionselectedID != -1) {
                                ColonyConditionRadioButton = (RadioButton) findViewById(ColonyConditionselectedID);
                                hiveData.put(colonyCondition, ColonyConditionRadioButton.getText().toString());
                            } else {
                                hiveData.put(colonyCondition, "");
                            }

                            //Actions and Notes
                            EditText ActionsAndNotes = (EditText) findViewById(R.id.ActionsNotes);
                            String Action = ActionsAndNotes.getText().toString();
                            hiveData.put(actionsNotes, Action);


                            Intent launchIntent = new Intent();
                            launchIntent.setClass(getApplicationContext(), EnvironmentalConditions.class);
                            startActivity(launchIntent);
                        }
                    }
                }).start();
            }
        });

    }

}
