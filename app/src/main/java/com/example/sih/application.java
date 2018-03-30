package com.example.sih;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Aneri Shah on 17/03/2018.
 */

public class application extends MultiDexApplication{
    private static application application;
    String baseUrl;
    SharedPreferences loginPreference;
    SharedPreferences userDataPreference;
    boolean loggedIn = false;
    public application(){
        baseUrl ="";
    }
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //  setUpLoginData();
    }

}
