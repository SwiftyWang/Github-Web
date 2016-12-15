package com.swifty.githubweb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.swifty.githubweb.storage.Pref;

public class MainActivity extends AppCompatActivity {

    public void addSplashFragment() {
        Fragment fragment = new SplashFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void addWebFragment() {
        Fragment fragment = new WebFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (TextUtils.isEmpty(Pref.getInstance().getUserName())) {
            addSplashFragment();
        } else {
            addWebFragment();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = getFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof IBackableFragment) {
            if (((IBackableFragment) fragment).onBackPressed()) {
                return;
            }
        }
        super.onBackPressed();
    }
}
