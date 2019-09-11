package com.swifty.mygithub2.storage;

import android.content.SharedPreferences;

import com.swifty.mygithub2.MyApplication;

/**
 * Created by swifty on 14/12/2016.
 */

public class Pref {
    private static final String PREF_IS_FIRST_TIME = "PREF_IS_FIRST_TIME";
    private static final String PREF_USERNAME = "PREF_USERNAME";

    private static Pref pref = new Pref();
    private SharedPreferences sharedPreferences;

    private Pref() {
        sharedPreferences = MyApplication.instance.getSharedPreferences("Pref", MyApplication.MODE_PRIVATE);
    }

    public static Pref getInstance() {
        return pref;
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public String getUserName() {
        return sharedPreferences.getString(PREF_USERNAME, "");
    }

    public boolean isFirstTime() {
        return sharedPreferences.getBoolean(PREF_IS_FIRST_TIME, true);
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void putUserName(String value) {
        sharedPreferences.edit().putString(PREF_USERNAME, value).apply();
    }

    public void setIsFirstTime(boolean isFirstTime) {
        sharedPreferences.edit().putBoolean(PREF_IS_FIRST_TIME, isFirstTime).apply();
    }
}
