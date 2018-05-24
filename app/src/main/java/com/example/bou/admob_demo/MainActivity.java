package com.example.bou.admob_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    String url;
    ProgressBar progressBar;
    AdView adView;
    InterstitialAd interstitialAd;
    FloatingActionButton floatingActionButton,floatingActionButton1;
    RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_views();
        init_variables();
        init_functions();
    }


    private void init_functions() {
        floatingActionButton.setVisibility(View.INVISIBLE);
        floatingActionButton1.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new MyWebViewClient());



        //Baner Add ----------------------------------------->
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);



        //Banner Add ended ---------------------------------->




        // interestitial add start ------------------------->


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                }else{
                    Toast.makeText(getApplicationContext(),"Not ready Yet",Toast.LENGTH_SHORT).show();
                }
            }
        });

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  // add unit id for the interstitial add
        final AdRequest adRequest1 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        interstitialAd.loadAd(adRequest1);

        interstitialAd.setAdListener(new AdListener(){
            public void onAdLoaded() {
                floatingActionButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                floatingActionButton.setVisibility(View.INVISIBLE);
                interstitialAd.loadAd(adRequest1);
            }
        });



         //interstetial add ended ----------------------------->




        // rewared add started ------------------------------->


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }else {
                    Toast.makeText(getApplicationContext(),"Failed to load",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //add unit id for the banner add





         rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                  floatingActionButton1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                floatingActionButton1.setVisibility(View.INVISIBLE);
                 loadInterstitialAdd();

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {
             //   floatingActionButton1.setVisibility(View.INVISIBLE);


            }
        });


        // rewared add end --------------------------------->

    }

    public void  loadInterstitialAdd(){
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }


   public void loadRewaredVideo(){
       rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }

    private void init_variables() {
        url = "https://www.google.com";
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");   //add id for the registered app
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);

    }

    private void init_views() {
        webView = (WebView)findViewById(R.id.webView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        adView = (AdView)findViewById(R.id.adView);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
    }

    public void setImmersiveMode(boolean b) {

    }

    private class MyWebViewClient extends WebViewClient {

        boolean loadingFinished = true;
        boolean redirect = false;
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            if (!loadingFinished) {
                redirect = true;
            }

            loadingFinished = false;
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(
                WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loadingFinished = false;
           // progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!redirect) {
                loadingFinished = true;
            }

            if (loadingFinished && !redirect) {
               progressBar.setVisibility(View.INVISIBLE);
            } else {
                redirect = false;
            }
        }
    }
}
