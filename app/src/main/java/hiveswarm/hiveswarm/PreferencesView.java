package hiveswarm.hiveswarm;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PreferencesView extends Fragment implements View.OnClickListener {

    public Button btnSave;
    public Button btnChange;

    public static final String hiveNumber = "hiveNumber";
    public static final String framesPerHive = "framesPerHive";
    public static final String hiveBodies = "hiveBodies";
    public static final String supers = "supers";
    public static final String location = "location";
    public static final String yearsOfBeekeeping = "yearsOfBeekeeping";

    public static final String username = "Username";

    public static final String tableName = "settings";

    public static View mView;

    public final static ParseObject SettingsData = ParseObject.create(tableName);

    public Context mContext;

    private int NumberOfHivesInt;
    private int FramesPerHiveInt;
    private int HiveBodiesInt;
    private int SupersInt;
    private int YearsOfBeekeepingInt;

    private List<String> values;


    public void preferenceChange() {
        EditText Location = (EditText) mView.findViewById(R.id.Location);
        String LocationString = Location.getText().toString();

        EditText NumberOfHives = (EditText) mView.findViewById(R.id.NumberOfHives);


        EditText FramesPerHive = (EditText) mView.findViewById(R.id.FramesPerHive);


        EditText HiveBodies = (EditText) mView.findViewById(R.id.HiveBodies);


        EditText YearsOfBeekeeping = (EditText) mView.findViewById(R.id.YearsOfBeekeeping);


        EditText Supers = (EditText) mView.findViewById(R.id.Supers);


        if (!YearsOfBeekeeping.getText().toString().equals("") || !NumberOfHives.getText().toString().equals("") || !FramesPerHive.getText().toString().equals("") || !HiveBodies.getText().toString().equals("") || !Supers.getText().toString().equals("")) {
            if (!NumberOfHives.getText().toString().equals("")) {
                NumberOfHivesInt = Integer.valueOf(NumberOfHives.getText().toString());
            }

            if (!FramesPerHive.getText().toString().equals("")) {
                FramesPerHiveInt = Integer.valueOf(FramesPerHive.getText().toString());
            }

            if (!HiveBodies.getText().toString().equals("")) {
                HiveBodiesInt = Integer.valueOf(HiveBodies.getText().toString());
            }

            if (!Supers.getText().toString().equals("")) {
                SupersInt = Integer.valueOf(Supers.getText().toString());
            }

            if (!YearsOfBeekeeping.getText().toString().equals("")) {
                YearsOfBeekeepingInt = Integer.valueOf(YearsOfBeekeeping.getText().toString());
            }

            SettingsData.put(username, SignInActivity.sign_email);
            SettingsData.put(PreferencesView.hiveNumber, NumberOfHivesInt);
            SettingsData.put(PreferencesView.framesPerHive, FramesPerHiveInt);
            SettingsData.put(PreferencesView.hiveBodies, HiveBodiesInt);
            SettingsData.put(PreferencesView.supers, SupersInt);
            SettingsData.put(PreferencesView.location, LocationString);
            SettingsData.put(PreferencesView.yearsOfBeekeeping, YearsOfBeekeepingInt);

            SettingsData.saveInBackground();

            setVisibleInvisible();


        } else {
            Toast.makeText(getContext(), "Please Fill in All Required Fields", Toast.LENGTH_SHORT).show();
        }

    }

    public void setVisibleInvisible() {
        TextView PreferenceTitle = (TextView) mView.findViewById(R.id.PreferencesTitle);

        PreferenceTitle.setVisibility(View.VISIBLE);

        RelativeLayout HiveNumber = (RelativeLayout) mView.findViewById(R.id.HiveNumber);

        HiveNumber.setVisibility(View.VISIBLE);

        RelativeLayout FramesPerHiveText = (RelativeLayout) mView.findViewById(R.id.FramesPerHiveText);

        FramesPerHiveText.setVisibility(View.VISIBLE);

        RelativeLayout HiveBodiesText = (RelativeLayout) mView.findViewById(R.id.HiveBodiesText);

        HiveBodiesText.setVisibility(View.VISIBLE);

        RelativeLayout SupersText = (RelativeLayout) mView.findViewById(R.id.SupersText);

        SupersText.setVisibility(View.VISIBLE);

        RelativeLayout LocationText = (RelativeLayout) mView.findViewById(R.id.LocationText);

        LocationText.setVisibility(View.VISIBLE);

        RelativeLayout YearsOfBeekeepingText = (RelativeLayout) mView.findViewById(R.id.YearsOfBeekeepingText);

        YearsOfBeekeepingText.setVisibility(View.VISIBLE);

        Button btnChange = (Button) mView.findViewById(R.id.btnChange);

        btnChange.setVisibility(View.VISIBLE);

        View break1 = (View) mView.findViewById(R.id.break1);

        break1.setVisibility(View.VISIBLE);

        View break2 = (View) mView.findViewById(R.id.break2);

        break2.setVisibility(View.VISIBLE);

        View break3 = (View) mView.findViewById(R.id.break3);

        break3.setVisibility(View.VISIBLE);

        View break4 = (View) mView.findViewById(R.id.break4);

        break4.setVisibility(View.VISIBLE);

        View break5 = (View) mView.findViewById(R.id.break5);

        break5.setVisibility(View.VISIBLE);

        //

        TextView PreferencesChangeTextView = (TextView) mView.findViewById(R.id.PreferencesChangeTextView);

        PreferencesChangeTextView.setVisibility(View.GONE);

        TextInputLayout YearsOfBeekeepingLayout = (TextInputLayout) mView.findViewById(R.id.YearsOfBeekeepingTextInputLayout);

        YearsOfBeekeepingLayout.setVisibility(View.GONE);

        TextInputLayout LocationLayout = (TextInputLayout) mView.findViewById(R.id.LocationTextInputLayout);

        LocationLayout.setVisibility(View.GONE);

        TextInputLayout SupersLayout = (TextInputLayout) mView.findViewById(R.id.SupersTextInputLayout);

        SupersLayout.setVisibility(View.GONE);

        TextInputLayout HiveBodiesLayout = (TextInputLayout) mView.findViewById(R.id.HiveBodiesTextInputLayout);

        HiveBodiesLayout.setVisibility(View.GONE);

        TextInputLayout FramesPerHiveLayout = (TextInputLayout) mView.findViewById(R.id.FramesPerHiveTextInputLayout);

        FramesPerHiveLayout.setVisibility(View.GONE);

        TextInputLayout HiveNumberLayout = (TextInputLayout) mView.findViewById(R.id.HiveNumberTextInputLayout);

        HiveNumberLayout.setVisibility(View.GONE);

        Button btnSave = (Button) mView.findViewById(R.id.btnSave);

        btnSave.setVisibility(View.GONE);

        try {
            showPreferences();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setInvisibleVisible() {
        TextView PreferenceTitle = (TextView) mView.findViewById(R.id.PreferencesTitle);

        PreferenceTitle.setVisibility(View.GONE);

        RelativeLayout HiveNumber = (RelativeLayout) mView.findViewById(R.id.HiveNumber);

        HiveNumber.setVisibility(View.GONE);

        RelativeLayout FramesPerHiveText = (RelativeLayout) mView.findViewById(R.id.FramesPerHiveText);

        FramesPerHiveText.setVisibility(View.GONE);

        RelativeLayout HiveBodiesText = (RelativeLayout) mView.findViewById(R.id.HiveBodiesText);

        HiveBodiesText.setVisibility(View.GONE);

        RelativeLayout SupersText = (RelativeLayout) mView.findViewById(R.id.SupersText);

        SupersText.setVisibility(View.GONE);

        RelativeLayout LocationText = (RelativeLayout) mView.findViewById(R.id.LocationText);

        LocationText.setVisibility(View.GONE);

        RelativeLayout YearsOfBeekeepingText = (RelativeLayout) mView.findViewById(R.id.YearsOfBeekeepingText);

        YearsOfBeekeepingText.setVisibility(View.GONE);

        Button btnChange = (Button) mView.findViewById(R.id.btnChange);

        btnChange.setVisibility(View.GONE);

        View break1 = (View) mView.findViewById(R.id.break1);

        break1.setVisibility(View.GONE);

        View break2 = (View) mView.findViewById(R.id.break2);

        break2.setVisibility(View.GONE);

        View break3 = (View) mView.findViewById(R.id.break3);

        break3.setVisibility(View.GONE);

        View break4 = (View) mView.findViewById(R.id.break4);

        break4.setVisibility(View.GONE);

        View break5 = (View) mView.findViewById(R.id.break5);

        break5.setVisibility(View.GONE);

        //

        TextView PreferencesChangeTextView = (TextView) mView.findViewById(R.id.PreferencesChangeTextView);

        PreferencesChangeTextView.setVisibility(View.VISIBLE);

        TextInputLayout YearsOfBeekeepingLayout = (TextInputLayout) mView.findViewById(R.id.YearsOfBeekeepingTextInputLayout);

        YearsOfBeekeepingLayout.setVisibility(View.VISIBLE);

        TextInputLayout LocationLayout = (TextInputLayout) mView.findViewById(R.id.LocationTextInputLayout);

        LocationLayout.setVisibility(View.VISIBLE);

        TextInputLayout SupersLayout = (TextInputLayout) mView.findViewById(R.id.SupersTextInputLayout);

        SupersLayout.setVisibility(View.VISIBLE);

        TextInputLayout HiveBodiesLayout = (TextInputLayout) mView.findViewById(R.id.HiveBodiesTextInputLayout);

        HiveBodiesLayout.setVisibility(View.VISIBLE);

        TextInputLayout FramesPerHiveLayout = (TextInputLayout) mView.findViewById(R.id.FramesPerHiveTextInputLayout);

        FramesPerHiveLayout.setVisibility(View.VISIBLE);

        TextInputLayout HiveNumberLayout = (TextInputLayout) mView.findViewById(R.id.HiveNumberTextInputLayout);

        HiveNumberLayout.setVisibility(View.VISIBLE);

        Button btnSave = (Button) mView.findViewById(R.id.btnSave);

        btnSave.setVisibility(View.VISIBLE);
    }


    public void showPreferences() throws ParseException {
        SettingsCollection settingsCollection = new SettingsCollection(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_preferences_view, container, false);

        this.mView = rootView;
        this.mContext = getContext();

        ParseQuery<settings> query = ParseQuery.getQuery(settings.class);
        query.whereEqualTo("Username", SignInActivity.sign_email);
        try {
            int row_count = query.count();
            if (row_count != 0) {
                setVisibleInvisible();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                preferenceChange();
                break;
            case R.id.btnChange:
                setInvisibleVisible();
                break;
            default:
                break;
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnSave = (Button) getActivity().findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnChange = (Button) getActivity().findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);
    }
}