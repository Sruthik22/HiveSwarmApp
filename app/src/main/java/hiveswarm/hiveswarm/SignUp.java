package hiveswarm.hiveswarm;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    public static final String tableName = "SignUp";
    public static final String SignUpName = "Name";
    public static final String SignUpEmail = "Email";
    public static final String SignUpPassword = "Password";
    public final static ParseObject SignUp = ParseObject.create(tableName);

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_up);
            ButterKnife.bind(this);

            TextView tx = (TextView)findViewById(R.id.HiveSwarmTitleText);

            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/title_font.ttf");

            tx.setTypeface(custom_font);

            _signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signup();
                }
            });

            _loginLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            });
        }

        public void signup() {
            Log.d(TAG, "Signup");

            if (!validate()) {
                onSignupFailed();
                return;
            }

            _signupButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(SignUp.this,
                    R.style.AppTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            String name = _nameText.getText().toString();
            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();

            ParseUser user = new ParseUser();
            user.setUsername(email);
            user.setEmail(email);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onSignupSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "There was an error signing up."
                                , Toast.LENGTH_LONG).show();
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onSignupFailed();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                    }
                }
            });





        }


        public void onSignupSuccess() {
            _signupButton.setEnabled(true);
            setResult(RESULT_OK, null);
            finish();
        }

        public void onSignupFailed() {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

            _signupButton.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;

            String name = _nameText.getText().toString();
            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();
            String reEnterPassword = _reEnterPasswordText.getText().toString();

            if (name.isEmpty() || name.length() < 3) {
                _nameText.setError("at least 3 characters");
                valid = false;
            } else {
                _nameText.setError(null);
            }


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

            if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
                _reEnterPasswordText.setError("Password Do not match");
                valid = false;
            } else {
                _reEnterPasswordText.setError(null);
            }

            return valid;
        }
    }
