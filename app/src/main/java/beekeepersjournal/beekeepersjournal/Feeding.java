package beekeepersjournal.beekeepersjournal;

import android.support.v7.app.AppCompatActivity;

public class Feeding extends AppCompatActivity {
    /*

    public static final String feedingOrMedication = "Feeding_and_or_Medication";
    public static final String reasonForFeeding = "Reason_for_Feeding";
    public static final String frequencyOfFeeding = "Frequency_of_Feeding";
    public static final String amountOfFoodFed = "Amount_Of_Food_Fed";
    public static final String typeOfFood = "Type_of_Food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);



        Button SaveButton = (Button) findViewById(R.id.btnSave);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Reason = (EditText) findViewById(R.id.Reason);
                String ReasonString = Reason.getText().toString();

                //FeedBeesRadioGroup
                RadioGroup FeedBeesRadioGroup = (RadioGroup) findViewById(R.id.FeedBeesRadioGroup);
                RadioButton FeedBeesRadioButton;
                int FeedBeesSelectedID = FeedBeesRadioGroup.getCheckedRadioButtonId();
                if (FeedBeesSelectedID != -1) {
                    FeedBeesRadioButton = (RadioButton) findViewById(FeedBeesSelectedID);
                    if (FeedBeesRadioButton.getText().toString().equals("Yes")) {
                        GeneralObservationsActivity.hiveData.put(feedingOrMedication, true);

                    }

                    else {
                        GeneralObservationsActivity.hiveData.put(feedingOrMedication, false);

                    }
                }

                else {
                    GeneralObservationsActivity.hiveData.put(feedingOrMedication, false);
                }

                EditText Frequency = (EditText) findViewById(R.id.Frequency);

                int FrequencyInt;
                if (Frequency.getText().toString().equals("")) {
                    FrequencyInt = 0;
                }

                else {
                    FrequencyInt = Integer.parseInt(Frequency.getText().toString());
                }


                EditText Amount = (EditText) findViewById(R.id.Amount);

                int AmountInt;
                if (Amount.getText().toString().equals("")) {
                    AmountInt = 0;
                }

                else {
                    AmountInt = Integer.parseInt(Amount.getText().toString());
                }

                EditText TypeOfFood = (EditText) findViewById(R.id.TypeOfFood);
                String TypeOfFoodString = TypeOfFood.getText().toString();

                GeneralObservationsActivity.hiveData.put(reasonForFeeding, ReasonString);
                GeneralObservationsActivity.hiveData.put(frequencyOfFeeding, FrequencyInt);
                GeneralObservationsActivity.hiveData.put(amountOfFoodFed, AmountInt);
                GeneralObservationsActivity.hiveData.put(typeOfFood, TypeOfFoodString);

                GeneralObservationsActivity.hiveData.saveInBackground();
                Intent launchIntent = new Intent(getApplicationContext(), Home.class);
                startActivity(launchIntent);
            }

        });
    }*/
}
