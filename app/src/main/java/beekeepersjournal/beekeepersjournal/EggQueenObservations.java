package beekeepersjournal.beekeepersjournal;

import android.support.v7.app.AppCompatActivity;

public class EggQueenObservations extends AppCompatActivity {

    /*public static final String queenSeen = "Queen_seen";
    public static final String queenMarked = "Queen_Marked";
    public static final String queenEggsSeen = "Queen_Eggs_seen";
    public static final String eggLarvaOrPupaSeen = "Egg_larva_or_pupa_seen";
    public static final String removedQueenCells = "Removed_Queen_Cells";
    public static final String queenCellsRemaining = "Queen_Cells_Remaining";
    public static final String supersedure = "Supersedure";
    public static final String spottyDroneBrood = "Spotty_Drone_Brood";
    public static final String broodInAllStages = "Worker_brood_in_all_stages";
    public static final String broodPattern = "Compact_Brood_Pattern";
    public static final String framesWithBrood = "Frames_with_Brood";
    public static final String framesUsedBroodChamber = "Frames_bees_occupy_in_brood_chamber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_queen_observations);
        Button launch = (Button) findViewById(R.id.btnSave);
        launch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Queen Seen
                RadioGroup QueenSeenRadioGroup = (RadioGroup) findViewById(R.id.QueenSeenRadioGroup);
                RadioButton QueenSeenRadioButton;
                int QueenSeenID = QueenSeenRadioGroup.getCheckedRadioButtonId();
                if (QueenSeenID != -1) {
                    QueenSeenRadioButton = (RadioButton) findViewById(QueenSeenID);
                    boolean QueenSeen;

                    if (QueenSeenRadioButton.getText().toString().equals("Yes")) {
                        QueenSeen = true;
                    }

                    else {
                        QueenSeen = false;
                    }

                    GeneralObservationsActivity.hiveData.put(queenSeen, QueenSeen);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(queenSeen, false);
                }

                //Queen Marked
                RadioGroup QueenMarkedRadioGroup = (RadioGroup) findViewById(R.id.QueenMarkedRadioGroup);
                RadioButton QueenMarkedRadioButton;
                int QueenMarkedID = QueenMarkedRadioGroup.getCheckedRadioButtonId();
                if (QueenMarkedID != -1) {
                    QueenMarkedRadioButton = (RadioButton) findViewById(QueenMarkedID);
                    boolean QueenMarked;

                    if (QueenMarkedRadioButton.getText().toString().equals("Yes")) {
                        QueenMarked = true;
                    }

                    else {
                        QueenMarked = false;
                    }

                    GeneralObservationsActivity.hiveData.put(queenMarked, QueenMarked);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(queenMarked, false);
                }



                //EggLarvaPupaSeen
                RadioGroup EggLarvaPupaSeenRadioGroup = (RadioGroup) findViewById(R.id.EggLarvaPupaSeenRadioGroup);
                RadioButton EggLarvaPupaSeenRadioButton;
                int EggLarvaPupaSeenID = EggLarvaPupaSeenRadioGroup.getCheckedRadioButtonId();
                if (EggLarvaPupaSeenID != -1) {
                    EggLarvaPupaSeenRadioButton = (RadioButton) findViewById(EggLarvaPupaSeenID);
                    boolean EggLarvaPupaSeen;

                    if (EggLarvaPupaSeenRadioButton.getText().toString().equals("Yes")) {
                        EggLarvaPupaSeen = true;
                    }

                    else {
                        EggLarvaPupaSeen = false;
                    }

                    GeneralObservationsActivity.hiveData.put(eggLarvaOrPupaSeen, EggLarvaPupaSeen);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(eggLarvaOrPupaSeen, false);
                }

                //EggLarvaPupaSeen
                RadioGroup RemovedQueenCellsRadioGroup = (RadioGroup) findViewById(R.id.RemovedQueenCellsRadioGroup);
                RadioButton RemovedQueenCellsRadioButton;
                int RemovedQueenCellsID = RemovedQueenCellsRadioGroup.getCheckedRadioButtonId();
                if (RemovedQueenCellsID != -1) {
                    RemovedQueenCellsRadioButton = (RadioButton) findViewById(RemovedQueenCellsID);
                    boolean RemovedQueenCells;

                    if (RemovedQueenCellsRadioButton.getText().toString().equals("Yes")) {
                        RemovedQueenCells = true;
                    }

                    else {
                        RemovedQueenCells = false;
                    }

                    GeneralObservationsActivity.hiveData.put(removedQueenCells, RemovedQueenCells);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(removedQueenCells, false);
                }

                EditText remainQueenCells = (EditText) findViewById(R.id.remainQueenCells);

                int remainQueenCellsInt;
                if (remainQueenCells.getText().toString().equals("")) {
                    remainQueenCellsInt = 0;
                }

                else {
                    remainQueenCellsInt = Integer.parseInt(remainQueenCells.getText().toString());
                }

                //supersedure
                RadioGroup supersedureRadioGroup = (RadioGroup) findViewById(R.id.supersedureRadioGroup);
                RadioButton supersedureRadioButton;
                int supersedureID = supersedureRadioGroup.getCheckedRadioButtonId();
                if (supersedureID != -1) {
                    supersedureRadioButton = (RadioButton) findViewById(supersedureID);
                    boolean supersedureBool;

                    if (supersedureRadioButton.getText().toString().equals("Yes")) {
                        supersedureBool = true;
                    }

                    else {
                        supersedureBool = false;
                    }

                    GeneralObservationsActivity.hiveData.put(supersedure, supersedureBool);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(supersedure, false);
                }

                //spottyDroneBrood
                RadioGroup SpottyDroneBroodRadioGroup = (RadioGroup) findViewById(R.id.SpottyDroneBroodRadioGroup);
                RadioButton SpottyDroneBroodRadioButton;
                int SpottyDroneBroodID = SpottyDroneBroodRadioGroup.getCheckedRadioButtonId();
                if (SpottyDroneBroodID != -1) {
                    SpottyDroneBroodRadioButton = (RadioButton) findViewById(SpottyDroneBroodID);
                    boolean SpottyDroneBrood;

                    if (SpottyDroneBroodRadioButton.getText().toString().equals("Yes")) {
                        SpottyDroneBrood = true;
                    }

                    else {
                        SpottyDroneBrood = false;
                    }

                    GeneralObservationsActivity.hiveData.put(spottyDroneBrood, SpottyDroneBrood);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(spottyDroneBrood, false);
                }

                EditText FramesWithBrood = (EditText) findViewById(R.id.FramesWithBrood);

                int FramesWithBroodInt;
                if (FramesWithBrood.getText().toString().equals("")) {
                    FramesWithBroodInt = 0;
                }

                else {
                    FramesWithBroodInt = Integer.parseInt(FramesWithBrood.getText().toString());
                }

                //brood in all stages
                RadioGroup BroodInAllStagesRadioGroup = (RadioGroup) findViewById(R.id.BroodInAllStagesRadioGroup);
                RadioButton BroodInAllStagesRadioButton;
                int BroodInAllStagesID = BroodInAllStagesRadioGroup.getCheckedRadioButtonId();
                if (BroodInAllStagesID != -1) {
                    BroodInAllStagesRadioButton = (RadioButton) findViewById(BroodInAllStagesID);
                    boolean BroodInAllStages;

                    if (BroodInAllStagesRadioButton.getText().toString().equals("Yes")) {
                        BroodInAllStages = true;
                    }

                    else {
                        BroodInAllStages = false;
                    }

                    GeneralObservationsActivity.hiveData.put(broodInAllStages, BroodInAllStages);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(broodInAllStages, false);
                }

                //brood pattern
                RadioGroup BroodPatternRadioGroup = (RadioGroup) findViewById(R.id.BroodPatternRadioGroup);
                RadioButton BroodPatternRadioButton;
                int BroodPatternSelectedID = BroodPatternRadioGroup.getCheckedRadioButtonId();
                if (BroodPatternSelectedID != -1) {
                    BroodPatternRadioButton = (RadioButton) findViewById(BroodPatternSelectedID);
                    boolean BroodPattern;

                    if (BroodPatternRadioButton.getText().toString().equals("Yes")) {
                        BroodPattern = true;
                    }

                    else {
                        BroodPattern = false;
                    }

                    GeneralObservationsActivity.hiveData.put(broodPattern, BroodPattern);
                }

                else {
                    GeneralObservationsActivity.hiveData.put(broodPattern, false);
                }

                EditText FramesUsedInBroodChamber = (EditText) findViewById(R.id.FramesUsedInBroodChamber);

                int FramesUsedInBroodChamberInt;
                if (FramesUsedInBroodChamber.getText().toString().equals("")) {
                    FramesUsedInBroodChamberInt = 0;
                }

                else {
                    FramesUsedInBroodChamberInt = Integer.parseInt(FramesUsedInBroodChamber.getText().toString());
                }

                GeneralObservationsActivity.hiveData.put(queenCellsRemaining, remainQueenCellsInt);
                GeneralObservationsActivity.hiveData.put(framesWithBrood, FramesWithBroodInt);
                GeneralObservationsActivity.hiveData.put(framesUsedBroodChamber, FramesUsedInBroodChamberInt);

                Intent launchIntent = new Intent();
                launchIntent.setClass(getApplicationContext(), Feeding.class);
                startActivity(launchIntent);
            }

        });




    }*/
}
