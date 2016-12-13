package com.swifty.githubweb;

import android.app.Application;

/**
 * Created by swifty on 14/12/2016.
 */

public class MyApplication extends Application{

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
