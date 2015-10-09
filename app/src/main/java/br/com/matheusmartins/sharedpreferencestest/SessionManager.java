package br.com.matheusmartins.sharedpreferencestest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by matheusmartins on 10/8/15.
 */
public class SessionManager {

    // SharedPreferences instance
    private SharedPreferences mSharedPreferences;

    // Context
    private Context mContext;

    // SharedPreferences name
    private static final String PREFERENCE_USER_CREDENTIALS_NAME = "UserCredentials";

    // All SharedPreferences Keys
    public static final String KEY_IS_LOGGED = "isLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public SessionManager(Context context){
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREFERENCE_USER_CREDENTIALS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String lastName, String email, String password){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        
        editor.putBoolean(KEY_IS_LOGGED, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }



    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){

        Boolean userIsNotLoggedIn = !this.isLoggedIn();

        if( userIsNotLoggedIn ){

            // user is not logged in redirect him to Login Activity
            Intent intentLoginActivity = new Intent(mContext, LoginActivity.class);

            // Closing all the Activities on history stack
            intentLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // This activity will become the start of a new task on this history stack.
            intentLoginActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            mContext.startActivity(intentLoginActivity);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> mapUserDetails = new HashMap<String, String>();

        mapUserDetails.put(KEY_NAME, mSharedPreferences.getString(KEY_NAME, null));
        mapUserDetails.put(KEY_LAST_NAME, mSharedPreferences.getString(KEY_LAST_NAME, null));
        mapUserDetails.put(KEY_EMAIL, mSharedPreferences.getString(KEY_EMAIL, null));
        mapUserDetails.put(KEY_PASSWORD, mSharedPreferences.getString(KEY_PASSWORD, null));


        return mapUserDetails;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        
        
        // Clearing all data from SharedPreferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent intentToLogin = new Intent(mContext, LoginActivity.class);

        // Closing all the Activities on history stack
        intentToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // This activity will become the start of a new task on this history stack.
        intentToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mContext.startActivity(intentToLogin);
    }

    // Get Login State
    public Boolean isLoggedIn(){
        return mSharedPreferences.getBoolean(KEY_IS_LOGGED, false);
    }
}
