package hiveswarm.hiveswarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PestsDiseases extends AppCompatActivity {

    public static final String varroaMite = "Are_there_Varroa_Mites";
    public static final String hiveBeetle = "Are_there_Hive_Beetles";
    public static final String varroaMiteAffect = "Varroa_Mite_Affect";
    public static final String hiveBeetleAffect = "Hive_Beetle_Affect";
    public static final String nosemaStreaking = "Nosema_Streaking";
    public static final String SignsDiesease = "Signs_of_diesease";
    public static final String AmountofVarroaMites = "Amount_of_Varroa_Mites";
    public static final String AmountofHiveBeetles = "Amount_of_Hive_Beetles";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pests_diseases);

        Button launch = (Button) findViewById(R.id.btnSave);
        launch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Varroa Mites
                RadioGroup VarroaMitesRadioGroup = (RadioGroup) findViewById(R.id.VarroaMitesRadioGroup);
                RadioButton VarroaMitesRadioButton;
                int VarroaMitesID = VarroaMitesRadioGroup.getCheckedRadioButtonId();
                if (VarroaMitesID != -1) {
                    VarroaMitesRadioButton = (RadioButton) findViewById(VarroaMitesID);
                    boolean VarroaMites;

                    if (VarroaMitesRadioButton.getText().toString().equals("Yes")) {
                        VarroaMites = true;
                    } else {
                        VarroaMites = false;
                    }

                    GeneralObservationsActivity.hiveData.put(varroaMite, VarroaMites);
                } else {
                    GeneralObservationsActivity.hiveData.put(varroaMite, false);
                }

                //Hive Beetles
                RadioGroup HiveBeetlesRadioGroup = (RadioGroup) findViewById(R.id.HiveBeetlesRadioGroup);
                RadioButton HiveBeetlesRadioButton;
                int HiveBeetleID = HiveBeetlesRadioGroup.getCheckedRadioButtonId();
                if (HiveBeetleID != -1) {
                    HiveBeetlesRadioButton = (RadioButton) findViewById(HiveBeetleID);
                    boolean HiveBeetles;

                    if (HiveBeetlesRadioButton.getText().toString().equals("Yes")) {
                        HiveBeetles = true;
                    } else {
                        HiveBeetles = false;
                    }

                    GeneralObservationsActivity.hiveData.put(hiveBeetle, HiveBeetles);
                } else {
                    GeneralObservationsActivity.hiveData.put(hiveBeetle, false);
                }

                //Nosema Streaking
                RadioGroup NosemaStreakingRadioGroup = (RadioGroup) findViewById(R.id.NosemaStreakingRadioGroup);
                RadioButton NosemaStreakingRadioButton;
                int NosemaStreakingID = NosemaStreakingRadioGroup.getCheckedRadioButtonId();
                if (NosemaStreakingID != -1) {
                    NosemaStreakingRadioButton = (RadioButton) findViewById(NosemaStreakingID);
                    boolean NosemaStreaking;

                    if (NosemaStreakingRadioButton.getText().toString().equals("Yes")) {
                        NosemaStreaking = true;
                    } else {
                        NosemaStreaking = false;
                    }

                    GeneralObservationsActivity.hiveData.put(nosemaStreaking, NosemaStreaking);
                } else {
                    GeneralObservationsActivity.hiveData.put(nosemaStreaking, false);
                }

                //Nosema Streaking
                RadioGroup DieseaseRadioGroup = (RadioGroup) findViewById(R.id.DieseaseRadioGroup);
                RadioButton DieseaseRadioButton;
                int DieseaseID = DieseaseRadioGroup.getCheckedRadioButtonId();
                if (DieseaseID != -1) {
                    DieseaseRadioButton = (RadioButton) findViewById(DieseaseID);
                    boolean Diesease;

                    if (DieseaseRadioButton.getText().toString().equals("Yes")) {
                        Diesease = true;
                    } else {
                        Diesease = false;
                    }

                    GeneralObservationsActivity.hiveData.put(SignsDiesease, Diesease);
                } else {
                    GeneralObservationsActivity.hiveData.put(SignsDiesease, false);
                }

                EditText VarroaMitesAffectEditText = (EditText) findViewById(R.id.VarroaMitesAffectEditText);
                String VarroaMitesAffectString = VarroaMitesAffectEditText.getText().toString();

                EditText HiveBeetlesAffectEditText = (EditText) findViewById(R.id.HiveBeetlesAffectEditText);
                String HiveBeetlesAffectString = HiveBeetlesAffectEditText.getText().toString();

                EditText VarroaMitesAmountEditText = (EditText) findViewById(R.id.VarroaMitesAmountEditText);
                int VarroaMitesAmountInt;
                if (VarroaMitesAmountEditText.getText().toString().equals("")) {
                    VarroaMitesAmountInt = 0;
                } else {
                    VarroaMitesAmountInt = Integer.parseInt(VarroaMitesAmountEditText.getText().toString());
                }


                EditText HiveBeetlesAmountEditText = (EditText) findViewById(R.id.HiveBeetlesAmountEditText);
                int HiveBeetlesAmountInt;
                if (HiveBeetlesAmountEditText.getText().toString().equals("")) {
                    HiveBeetlesAmountInt = 0;
                } else {
                    HiveBeetlesAmountInt = Integer.parseInt(HiveBeetlesAmountEditText.getText().toString());
                }

                GeneralObservationsActivity.hiveData.put(varroaMiteAffect, VarroaMitesAffectString);
                GeneralObservationsActivity.hiveData.put(hiveBeetleAffect, HiveBeetlesAffectString);
                GeneralObservationsActivity.hiveData.put(AmountofVarroaMites, VarroaMitesAmountInt);
                GeneralObservationsActivity.hiveData.put(AmountofHiveBeetles, HiveBeetlesAmountInt);

                Intent launchIntent = new Intent();
                launchIntent.setClass(getApplicationContext(), EggQueenObservations.class);
                startActivity(launchIntent);
            }

        });

    }
}
