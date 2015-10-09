package br.com.matheusmartins.sharedpreferencestest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {


    private SessionManager mSession;
    private Button mBtnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        mSession = new SessionManager(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        // Button logout
        mBtnLogout = (Button) findViewById(R.id.btnLogout);

        //Toast.makeText(getApplicationContext(), "User Login Status: " + mSession.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        mSession.checkLogin();

        // get user data from session
        HashMap<String, String> user = mSession.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);
        // email
        String email = user.get(SessionManager.KEY_EMAIL);




        /**
         * Logout button click event
         * */
        mBtnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                mSession.logoutUser();
            }
        });
    }

}
