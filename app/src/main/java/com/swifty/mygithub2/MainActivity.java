package com.swifty.mygithub2;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.swifty.mygithub2.storage.Pref;

public class MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

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
        Fragment fragment = getFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof IBackableFragment) {
            if (((IBackableFragment) fragment).onBackPressed()) {
                return;
            }
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.press_again_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
