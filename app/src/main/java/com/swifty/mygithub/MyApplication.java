package com.swifty.mygithub;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

/**
 * Created by swifty on 14/12/2016.
 */

public class MyApplication extends Application{

    public static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, APIClass.PUBLISHER_ID);
        instance = this;
    }
}
