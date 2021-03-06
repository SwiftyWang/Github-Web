package com.swifty.mygithub2;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;
import com.swifty.mygithub2.storage.Pref;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by swifty on 14/12/2016.
 */

public class WebFragment extends Fragment implements AdvancedWebView.Listener, IBackableFragment {

    private InterstitialAd mInterstitialAd;
    private AdvancedWebView mWebView;
    private View progress;

    private void initAds(View view) {
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
        if (!Pref.getInstance().isFirstTime()) {
            showFullScreenAds();
        }
        Pref.getInstance().setIsFirstTime(false);
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(APIClass.XIAOMI_DEVICE_ID)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void showFullScreenAds() {
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.full_screen_ads_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
        requestNewInterstitial();
    }
}
