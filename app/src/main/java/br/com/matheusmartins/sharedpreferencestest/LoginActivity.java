package br.com.matheusmartins.sharedpreferencestest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by matheusmartins on 10/8/15.
 */
public class LoginActivity extends Activity {


    private EditText mInputEmail, mInputPassword;
    private Button mBtnLogin;
    private SessionManager mSession;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Session Manager
        mSession = new SessionManager(getApplicationContext());

        // Email, Password input text
        mInputEmail = (EditText) findViewById(R.id.login_activity_email_input);
        mInputPassword = (EditText) findViewById(R.id.login_activity_password_input);

        Toast.makeText(getApplicationContext(), "User Login Status: " + mSession.isLoggedIn(), Toast.LENGTH_LONG).show();


        // Login button
        mBtnLogin = (Button) findViewById(R.id.login_activity_login_button);


        // Login button click event
        mBtnLogin.setOnClickListener(onLoginClickListener);

    }


    View.OnClickListener onLoginClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {

            // Get username, password from EditText
            String username = mInputEmail.getText().toString();
            String password = mInputPassword.getText().toString();

            // Check if username, password is filled
            if( !username.isEmpty() && !password.isEmpty() ){
                // For testing puspose username, password is checked with sample data
                // username = test
                // password = test
                if( username.equals("test") && password.equals("test")){

                    // Creating user login mSession
                    // For testing i am stroing name, email as follow
                    // Use user real data
                    // Staring MainActivity
                    mSession.createLoginSession("namTest", "lastNameTest", "emailTest", "passwordTest");
                    Intent intentToMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentToMainActivity);
                    finish();

                }else{
                    Toast.makeText( LoginActivity.this, "Username/Password is incorrect", Toast.LENGTH_SHORT ).show();
                }
            }else{
                Toast.makeText( LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT ).show();
            }
        }
    };
}
