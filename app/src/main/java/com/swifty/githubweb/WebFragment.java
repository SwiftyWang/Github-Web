package com.swifty.githubweb;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.swifty.githubweb.storage.Pref;

/**
 * Created by swifty on 14/12/2016.
 */

public class WebFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        WebView view = (WebView) inflater.inflate(R.layout.web_fragment, container, false);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("https://github-e.com/#/user/" + Pref.getInstance().getUserName());
        return view;
    }
}
