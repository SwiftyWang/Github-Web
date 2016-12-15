package com.swifty.mygithub;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.swifty.mygithub.storage.Pref;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by swifty on 14/12/2016.
 */

public class WebFragment extends Fragment implements AdvancedWebView.Listener, IBackableFragment {

    private AdvancedWebView mWebView;
    private View progress;

    private void initAds(View view) {
        NativeExpressAdView adView = (NativeExpressAdView) view.findViewById(R.id.adView);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice(APIClass.MEIZU_DEVICE_ID)
                .build();
        adView.loadAd(request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public boolean onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.web_fragment, container, false);
        mWebView = (AdvancedWebView) view.findViewById(R.id.webview);
        mWebView.loadUrl("https://github-e.com/#/user/" + Pref.getInstance().getUserName());
        mWebView.setListener(this, this);
        progress = view.findViewById(R.id.progress);
        initAds(view);
        return view;
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onPageFinished(String url) {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPause() {
        mWebView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }
}
