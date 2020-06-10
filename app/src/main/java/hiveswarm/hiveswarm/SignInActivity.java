
package hiveswarm.hiveswarm;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import android.app.ProgressDialog;
import android.util.Log;

import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private boolean worked;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    public static String sign_email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        TextView tx = (TextView)findViewById(R.id.HiveSwarmTitleText);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/title_font.ttf");

        tx.setTypeface(custom_font);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, com.parse.ParseException e) {
                if (user != null) {
                    worked = true;

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    sign_email = email;
                                        onLoginSuccess();
                                    progressDialog.dismiss();
                                }
                            }, 3000);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "There was an error logging in.",
                            Toast.LENGTH_LONG).show();
                    onLoginFailed();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void finish() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}