package com.swifty.githubweb.storage;

import android.content.SharedPreferences;

import com.swifty.githubweb.MyApplication;

/**
 * Created by swifty on 14/12/2016.
 */

public class Pref {
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

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void putUserName(String value) {
        sharedPreferences.edit().putString(PREF_USERNAME, value).apply();
    }
}
