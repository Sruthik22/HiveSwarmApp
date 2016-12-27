package hiveswarm.hiveswarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SingleItemView extends ActionBarActivity {
    // Declare Variables
    String Hive_ID;
    String CreationDate;
    String objectId;
    List<hive_data> ob;
    ProgressDialog mProgressDialog;

    List<String> values  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        Intent i = getIntent();
        // Get the result of rank

        objectId = i.getStringExtra("ObjectId");
        Hive_ID = i.getStringExtra("Hive_ID");
        // Get the result of country
        CreationDate = i.getStringExtra("CreationDate");

        // Locate the TextViews in singleitemview.xml
        TextView txthive = (TextView) findViewById(R.id.hive_Id);
        TextView txtcreation = (TextView) findViewById(R.id.creation_date);

        // Set results to the TextViews
        txthive.setText(Hive_ID);
        txtcreation.setText(CreationDate);

        values.add(Hive_ID);
        values.add(CreationDate);

        ParseQuery<hive_data> query = ParseQuery.getQuery(hive_data.class);
        query.whereEqualTo("Username", SignInActivity.Display_Name);
        query.whereEqualTo("objectId", objectId);

        try {
            ob = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final hive_data hive_data = ob.get(0);

        TextView pollenAmount = (TextView) findViewById(R.id.pollenAmount);


        if (hive_data.getPollenAmount().equals("")) {
            pollenAmount.setText("");
            values.add("");
        } else {
            pollenAmount.setText(hive_data.getPollenAmount());
            values.add(hive_data.getPollenAmount());
        }


        TextView honeyAmount = (TextView) findViewById(R.id.honeyAmount);


        if (hive_data.getAmountOfHoney().equals(null)) {
            honeyAmount.setText("");
            values.add("");
        } else {
            honeyAmount.setText(hive_data.getAmountOfHoney());
            values.add(hive_data.getAmountOfHoney());
        }

        TextView pestCount = (TextView) findViewById(R.id.pestCount);


        if (hive_data.getPestCount().equals(null)) {
            pestCount.setText("");
            values.add("");
        } else {
            pestCount.setText(hive_data.getPestCount());
            values.add(hive_data.getPestCount());
        }

        TextView beeCount = (TextView) findViewById(R.id.beeCount);


        if (hive_data.getPestCount().equals(null)) {
            beeCount.setText("");
            values.add("");
        } else {
            beeCount.setText(hive_data.getBeeCount());
            values.add(hive_data.getBeeCount());
        }

        TextView emergency = (TextView) findViewById(R.id.emergency);


        if (String.valueOf(hive_data.getEmergency()).equals(null)) {
            emergency.setText("");
            values.add("");
        } else {
            emergency.setText(String.valueOf(hive_data.getEmergency()));
            values.add(String.valueOf(hive_data.getEmergency()));
        }

        TextView swarm = (TextView) findViewById(R.id.swarm);


        if (String.valueOf(hive_data.getSwarm()).equals(null)) {
            swarm.setText("");
            values.add("");
        } else {
            swarm.setText(String.valueOf(hive_data.getSwarm()));
            values.add(String.valueOf(hive_data.getSwarm()));
        }


        TextView NumberOfFramesWithFoundation = (TextView) findViewById(R.id.NumberOfFramesWithFoundation);

        if (String.valueOf(hive_data.getFramesOfFoundation()).equals(null)) {
            NumberOfFramesWithFoundation.setText("");
            values.add("");
        } else {
            NumberOfFramesWithFoundation.setText(String.valueOf(hive_data.getFramesOfFoundation()));
            values.add(String.valueOf(hive_data.getFramesOfFoundation()));
        }


        TextView NumberofFrameswithHoneyorNectar = (TextView) findViewById(R.id.NumberofFrameswithHoneyorNectar);

        if (String.valueOf(hive_data.getFramesWithHoney()).equals(null)) {
            NumberofFrameswithHoneyorNectar.setText("");
            values.add("");
        } else {
            NumberofFrameswithHoneyorNectar.setText(String.valueOf(hive_data.getFramesWithHoney()));
            values.add(String.valueOf(hive_data.getFramesWithHoney()));
        }

        TextView NumberofFrameswithPollen = (TextView) findViewById(R.id.NumberofFrameswithPollen);

        if (String.valueOf(hive_data.getFramesOfPollen()).equals(null)) {
            NumberofFrameswithPollen.setText("");
            values.add("");
        } else {
            NumberofFrameswithPollen.setText(String.valueOf(hive_data.getFramesOfPollen()));
            values.add(String.valueOf(hive_data.getFramesOfPollen()));
        }


        TextView NumberofFrameswithOpenComb = (TextView) findViewById(R.id.NumberofFrameswithOpenComb);
        NumberofFrameswithOpenComb.setText(String.valueOf(hive_data.getFramesOpenComb()));

        if (String.valueOf(hive_data.getFramesOpenComb()).equals(null)) {
            NumberofFrameswithOpenComb.setText("");
            values.add("");
        } else {
            NumberofFrameswithOpenComb.setText(String.valueOf(hive_data.getFramesOpenComb()));
            values.add(String.valueOf(hive_data.getFramesOpenComb()));
        }

        TextView NumberofSupersinPlace = (TextView) findViewById(R.id.NumberofSupersinPlace);

        if (String.valueOf(hive_data.getSupersInPlace()).equals(null)) {
            NumberofSupersinPlace.setText("");
            values.add("");
        } else {
            NumberofSupersinPlace.setText(String.valueOf(hive_data.getSupersInPlace()));
            values.add(String.valueOf(hive_data.getSupersInPlace()));
        }


        TextView NumberOfSupersAdded = (TextView) findViewById(R.id.NumberOfSupersAdded);

        if (String.valueOf(hive_data.getSupersAdded()).equals(null)) {
            NumberOfSupersAdded.setText("");
            values.add("");
        } else {
            NumberOfSupersAdded.setText(String.valueOf(hive_data.getSupersAdded()));
            values.add(String.valueOf(hive_data.getSupersAdded()));
        }


        TextView TemperOfBees = (TextView) findViewById(R.id.TemperOfBees);

        if (String.valueOf(hive_data.getTemper()).equals(null)) {
            TemperOfBees.setText("");
            values.add("");
        } else {
            TemperOfBees.setText(String.valueOf(hive_data.getTemper()));
            values.add(String.valueOf(hive_data.getTemper()));
        }


        TextView ColonyStrength = (TextView) findViewById(R.id.ColonyStrength);

        if (String.valueOf(hive_data.getColonyCondition()).equals(null)) {
            ColonyStrength.setText("");
            values.add("");
        } else {
            ColonyStrength.setText(String.valueOf(hive_data.getColonyCondition()));
            values.add(String.valueOf(hive_data.getColonyCondition()));
        }


        TextView ActionsNotes = (TextView) findViewById(R.id.ActionsNotes);

        if (String.valueOf(hive_data.getActionsAndNotes()).equals(null)) {
            ActionsNotes.setText("");
            values.add("");
        } else {
            ActionsNotes.setText(String.valueOf(hive_data.getActionsAndNotes()));
            values.add(String.valueOf(hive_data.getActionsAndNotes()));
        }


        TextView Temperature = (TextView) findViewById(R.id.Temperature);

        if (String.valueOf(hive_data.getWeatherTemperature()).equals(null)) {
            Temperature.setText("");
            values.add("");
        } else {
            Temperature.setText(String.valueOf(hive_data.getWeatherTemperature()));
            values.add(String.valueOf(hive_data.getWeatherTemperature()));
        }


        TextView Humidity = (TextView) findViewById(R.id.Humidity);

        if (String.valueOf(hive_data.getWeatherHumidity()).equals(null)) {
            Humidity.setText("");
            values.add("");
        } else {
            Humidity.setText(String.valueOf(hive_data.getWeatherHumidity()));
            values.add(String.valueOf(hive_data.getWeatherHumidity()));
        }


        TextView Precipitation = (TextView) findViewById(R.id.Precipitation);

        if (String.valueOf(hive_data.getWeatherPrecipitation()).equals(null)) {
            Precipitation.setText("");
            values.add("");
        } else {
            Precipitation.setText(String.valueOf(hive_data.getWeatherPrecipitation()));
            values.add(String.valueOf(hive_data.getWeatherPrecipitation()));
        }


        TextView CloudCoverage = (TextView) findViewById(R.id.CloudCoverage);

        if (String.valueOf(hive_data.getWeatherCloudiness()).equals(null)) {
            CloudCoverage.setText("");
            values.add("");
        } else {
            CloudCoverage.setText(String.valueOf(hive_data.getWeatherCloudiness()));
            values.add(String.valueOf(hive_data.getWeatherCloudiness()));
        }

        TextView VarroaMites = (TextView) findViewById(R.id.VarroaMites);

        if (String.valueOf(hive_data.getAreThereVarroaMites()).equals(null)) {
            VarroaMites.setText("");
            values.add("");
        } else {
            VarroaMites.setText(String.valueOf(hive_data.getAreThereVarroaMites()));
            values.add(String.valueOf(hive_data.getAreThereVarroaMites()));
        }

        TextView HiveBeetles = (TextView) findViewById(R.id.HiveBeetles);

        if (String.valueOf(hive_data.getAreThereHiveBeetles()).equals(null)) {
            HiveBeetles.setText("");
            values.add("");
        } else {
            HiveBeetles.setText(String.valueOf(hive_data.getAreThereHiveBeetles()));
            values.add(String.valueOf(hive_data.getAreThereHiveBeetles()));
        }

        TextView HiveBeetlesAmount = (TextView) findViewById(R.id.HiveBeetlesAmount);

        if (String.valueOf(hive_data.getAmountOfHiveBeetles()).equals(null)) {
            HiveBeetlesAmount.setText("");
            values.add("");
        } else {
            HiveBeetlesAmount.setText(String.valueOf(hive_data.getAmountOfHiveBeetles()));
            values.add(String.valueOf(hive_data.getAmountOfHiveBeetles()));
        }

        TextView HiveBeetlesAffect = (TextView) findViewById(R.id.HiveBeetlesAffect);

        if (String.valueOf(hive_data.getHiveBeetleAffect()).equals(null)) {
            HiveBeetlesAffect.setText("");
            values.add("");
        } else {
            HiveBeetlesAffect.setText(String.valueOf(hive_data.getHiveBeetleAffect()));
            values.add(String.valueOf(hive_data.getHiveBeetleAffect()));
        }

        TextView VarroaMitesAmount = (TextView) findViewById(R.id.VarroaMitesAmount);

        if (String.valueOf(hive_data.getAmountOfVarroaMite()).equals(null)) {
            VarroaMitesAmount.setText("");
            values.add("");
        } else {
            VarroaMitesAmount.setText(String.valueOf(hive_data.getAmountOfVarroaMite()));
            values.add(String.valueOf(hive_data.getAmountOfVarroaMite()));
        }

        TextView VarroaMitesAffect = (TextView) findViewById(R.id.VarroaMitesAffect);

        if (String.valueOf(hive_data.getVarroaMiteAffect()).equals(null)) {
            VarroaMitesAffect.setText("");
            values.add("");
        } else {
            VarroaMitesAffect.setText(String.valueOf(hive_data.getVarroaMiteAffect()));
            values.add(String.valueOf(hive_data.getVarroaMiteAffect()));
        }

        TextView SignsOfDisease = (TextView) findViewById(R.id.SignsOfDisease);

        if (String.valueOf(hive_data.getSignsOfDiesease()).equals(null)) {
            SignsOfDisease.setText("");
            values.add("");
        } else {
            SignsOfDisease.setText(String.valueOf(hive_data.getSignsOfDiesease()));
            values.add(String.valueOf(hive_data.getSignsOfDiesease()));
        }

        TextView NosemaStreaking = (TextView) findViewById(R.id.NosemaStreaking);

        if (String.valueOf(hive_data.getNosemaStreaking()).equals(null)) {
            NosemaStreaking.setText("");
            values.add("");
        } else {
            NosemaStreaking.setText(String.valueOf(hive_data.getNosemaStreaking()));
            values.add(String.valueOf(hive_data.getNosemaStreaking()));
        }

        TextView QueenSeen = (TextView) findViewById(R.id.QueenSeen);

        if (String.valueOf(hive_data.getQueenSeen()).equals(null)) {
            QueenSeen.setText("");
            values.add("");
        } else {
            QueenSeen.setText(String.valueOf(hive_data.getQueenSeen()));
            values.add(String.valueOf(hive_data.getQueenSeen()));
        }

        TextView QueenMarked = (TextView) findViewById(R.id.QueenMarked);

        if (String.valueOf(hive_data.getQueenSeen()).equals(null)) {
            QueenSeen.setText("");
            values.add("");
        } else {
            QueenSeen.setText(String.valueOf(hive_data.getQueenSeen()));
            values.add(String.valueOf(hive_data.getQueenSeen()));
        }

        TextView QueenEggsSeen = (TextView) findViewById(R.id.QueenEggsSeen);

        if (String.valueOf(hive_data.getQueenSeen()).equals(null)) {
            QueenEggsSeen.setText("");
            values.add("");
        } else {
            QueenEggsSeen.setText(String.valueOf(hive_data.getQueenSeen()));
            values.add(String.valueOf(hive_data.getQueenSeen()));
        }

        TextView EggLarvaPupaSeen = (TextView) findViewById(R.id.EggLarvaPupaSeen);

        if (String.valueOf(hive_data.getEggLarvaOrPupaSeen()).equals(null)) {
            EggLarvaPupaSeen.setText("");
            values.add("");
        } else {
            EggLarvaPupaSeen.setText(String.valueOf(hive_data.getEggLarvaOrPupaSeen()));
            values.add(String.valueOf(hive_data.getEggLarvaOrPupaSeen()));
        }

        TextView RemovedQueenCells = (TextView) findViewById(R.id.RemovedQueenCells);

        if (String.valueOf(hive_data.getRemovedQueenCells()).equals(null)) {
            RemovedQueenCells.setText("");
            values.add("");
        } else {
            RemovedQueenCells.setText(String.valueOf(hive_data.getRemovedQueenCells()));
            values.add(String.valueOf(hive_data.getRemovedQueenCells()));
        }

        TextView remainQueenCells = (TextView) findViewById(R.id.remainQueenCells);

        if (String.valueOf(hive_data.getQueenCellsRemaining()).equals(null)) {
            remainQueenCells.setText("");
            values.add("");
        } else {
            remainQueenCells.setText(String.valueOf(hive_data.getQueenCellsRemaining()));
            values.add(String.valueOf(hive_data.getQueenCellsRemaining()));
        }

        TextView supersedure = (TextView) findViewById(R.id.supersedure);

        if (String.valueOf(hive_data.getSupersedure()).equals(null)) {
            supersedure.setText("");
            values.add("");
        } else {
            supersedure.setText(String.valueOf(hive_data.getSupersedure()));
            values.add(String.valueOf(hive_data.getSupersedure()));
        }

        TextView SpottyDroneBrood = (TextView) findViewById(R.id.SpottyDroneBrood);

        if (String.valueOf(hive_data.getSpottyDroneBrood()).equals(null)) {
            SpottyDroneBrood.setText("");
            values.add("");
        } else {
            SpottyDroneBrood.setText(String.valueOf(hive_data.getSpottyDroneBrood()));
            values.add(String.valueOf(hive_data.getSpottyDroneBrood()));
        }

        TextView FramesWithBrood = (TextView) findViewById(R.id.FramesWithBrood);

        if (String.valueOf(hive_data.getFramesWithBrood()).equals(null)) {
            FramesWithBrood.setText("");
            values.add("");
        } else {
            FramesWithBrood.setText(String.valueOf(hive_data.getFramesWithBrood()));
            values.add(String.valueOf(hive_data.getFramesWithBrood()));
        }

        TextView BroodInAllStages = (TextView) findViewById(R.id.BroodInAllStages);

        if (String.valueOf(hive_data.getBroodInAllStages()).equals(null)) {
            BroodInAllStages.setText("");
            values.add("");
        } else {
            BroodInAllStages.setText(String.valueOf(hive_data.getBroodInAllStages()));
            values.add(String.valueOf(hive_data.getBroodInAllStages()));
        }

        TextView BroodPattern = (TextView) findViewById(R.id.BroodPattern);

        if (String.valueOf(hive_data.getCompactBroodPattern()).equals(null)) {
            BroodPattern.setText("");
            values.add("");
        } else {
            BroodPattern.setText(String.valueOf(hive_data.getCompactBroodPattern()));
            values.add(String.valueOf(hive_data.getCompactBroodPattern()));
        }

        TextView FramesUsedInBroodChamber = (TextView) findViewById(R.id.FramesUsedInBroodChamber);

        if (String.valueOf(hive_data.getFramesWithBrood()).equals(null)) {
            FramesUsedInBroodChamber.setText("");
            values.add("");
        } else {
            FramesUsedInBroodChamber.setText(String.valueOf(hive_data.getFramesWithBrood()));
            values.add(String.valueOf(hive_data.getFramesWithBrood()));
        }

        TextView FeedBees = (TextView) findViewById(R.id.FeedBees);

        if (String.valueOf(hive_data.getFeedingOrMedication()).equals(null)) {
            FeedBees.setText("");
            values.add("");
        } else {
            FeedBees.setText(String.valueOf(hive_data.getFeedingOrMedication()));
            values.add(String.valueOf(hive_data.getFeedingOrMedication()));
        }

        TextView ReasonToFeedBees = (TextView) findViewById(R.id.ReasonToFeedBees);

        if (String.valueOf(hive_data.getReasonForFeeding()).equals(null)) {
            ReasonToFeedBees.setText("");
            values.add("");
        } else {
            ReasonToFeedBees.setText(String.valueOf(hive_data.getReasonForFeeding()));
            values.add(String.valueOf(hive_data.getReasonForFeeding()));
        }

        TextView Frequency = (TextView) findViewById(R.id.Frequency);

        if (String.valueOf(hive_data.getFrequencyOfFeeding()).equals(null)) {
            Frequency.setText("");
            values.add("");
        } else {
            Frequency.setText(String.valueOf(hive_data.getFrequencyOfFeeding()));
            values.add(String.valueOf(hive_data.getFrequencyOfFeeding()));
        }

        TextView AmountFeeding = (TextView) findViewById(R.id.AmountFeeding);

        if (String.valueOf(hive_data.getAmountOfFoodFed()).equals(null)) {
            AmountFeeding.setText("");
            values.add("");
        } else {
            AmountFeeding.setText(String.valueOf(hive_data.getAmountOfFoodFed()));
            values.add(String.valueOf(hive_data.getAmountOfFoodFed()));
        }

        TextView TypeOfFood = (TextView) findViewById(R.id.TypeOfFood);

        if (String.valueOf(hive_data.getTypeOfFood()).equals(null)) {
            TypeOfFood.setText("");
            values.add("");
        } else {
            TypeOfFood.setText(String.valueOf(hive_data.getTypeOfFood()));
            values.add(String.valueOf(hive_data.getTypeOfFood()));
        }
    }

                @Override
                public boolean onCreateOptionsMenu (Menu menu){
                    getMenuInflater().inflate(R.menu.sharemenu, menu);
                    return true;
                }

                @Override
                public boolean onOptionsItemSelected (MenuItem item){
                    // handle item selection
                    switch (item.getItemId()) {
                        case R.id.Share:
                            saveToFile("File Name");
                            return true;
                        default:
                            return super.onOptionsItemSelected(item);
                    }
                }

            public void saveToFile (String fileName){

                new SaveToCSV().execute();
            }

            // RemoteDataTask AsyncTask
            private class SaveToCSV extends AsyncTask<Void, Void, Void> {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    // Create a progressdialog
                    mProgressDialog = new ProgressDialog(SingleItemView.this);
                    // Set progressdialog title
                    mProgressDialog.setTitle("Saving to CSV File");
                    // Set progressdialog message
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setIndeterminate(false);
                    // Show progressdialog
                    mProgressDialog.show();
                }

                @Override
                protected Void doInBackground(Void... params) {

                    String columnString =   "";
                    String dataString   =   "";


                    int i = 0;

                    for (Field field : R.string.class.getDeclaredFields()) {
                        if (Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers()) && field.getType().equals(int.class)) {
                            try {
                                if (field.getName().startsWith("hs_")) {
                                    i += 1;
                                    int id = field.getInt(null);
                                    columnString += "\"" + getResources().getString(id) + "\",";
                                    dataString += "\"" + values.get(i-1) + "\",";
                                }
                            } catch (IllegalArgumentException e) {
                                // ignore
                            } catch (IllegalAccessException e) {
                                // ignore
                            }
                        }
                    }

                    String combinedString = columnString + "\n" + dataString;

                    File file   = null;
                    File root   = Environment.getExternalStorageDirectory();
                    if (root.canWrite()){
                        File dir    =   new File (root.getAbsolutePath() + "/PersonData");
                        dir.mkdirs();
                        file   =   new File(dir, "Data.csv");
                        FileOutputStream out   =   null;
                        try {
                            out = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.write(combinedString.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (file != null) {
                        Log.e("not nl", "");
                    }

                    else {
                        Log.e("not nl", "yes");
                    }

                    Uri u1  =   Uri.fromFile(file);

                    Log.e("SingleItemView", "In Try: Going to Send Intent");

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Hive Data CSV File - Created at - " + CreationDate);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, u1);
                    sendIntent.setType("text/html");
                    startActivity(sendIntent);

                    file.delete();

                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    //Email client show.
                    mProgressDialog.dismiss();
                }
            }
        }
