package com.example.bou.admob_demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    String url;
    ProgressBar progressBar;
    AdView adView;
    InterstitialAd interstitialAd;
    FloatingActionButton floatingActionButton,floatingActionButton1;
    RewardedVideoAd rewardedVideoAd;
    int addClicked ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences1;
    SharedPreferences sharedPreferences2;
    boolean conditionMatched;

    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Email = intent.getStringExtra("email");

        init_views();
        init_variables();
        init_functions();
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("YEs", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void init_functions() {
        floatingActionButton.setVisibility(View.INVISIBLE);
        floatingActionButton1.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        progressBar.setVisibility(View.INVISIBLE);
      //  webView.setWebViewClient(new MyWebViewClient());



        //Baner Add ----------------------------------------->
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);



        //Banner Add ended ---------------------------------->




        // interestitial add start ------------------------->


        showingInterstitialAdd();

//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(interstitialAd.isLoaded()){
//                    interstitialAd.show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"Not ready Yet",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });




//
//        interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  // add unit id for the interstitial add
//        final AdRequest adRequest1 = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
//        interstitialAd.loadAd(adRequest1);
//
//        interstitialAd.setAdListener(new AdListener(){
//            public void onAdLoaded() {
//                floatingActionButton.setVisibility(View.INVISIBLE);
//                interstitialAd.show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when the ad is displayed.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//               Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();// Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                floatingActionButton.setVisibility(View.INVISIBLE);
//                SystemClock.sleep(10000);
//                interstitialAd.loadAd(adRequest1);
//            }
//        });
//


         //interstetial add ended ----------------------------->




        // rewared add started ------------------------------->


//        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(rewardedVideoAd.isLoaded()) {
//                    rewardedVideoAd.show();
//                }else {
//                    Toast.makeText(getApplicationContext(),"Failed to load",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //add unit id for the banner add





        // rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());

//        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
//            @Override
//            public void onRewardedVideoAdLoaded() {
//                  floatingActionButton1.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onRewardedVideoAdOpened() {
//
//            }
//
//            @Override
//            public void onRewardedVideoStarted() {
//
//            }
//
//            @Override
//            public void onRewardedVideoAdClosed() {
//                floatingActionButton1.setVisibility(View.INVISIBLE);
//                 loadRewaredVideo();
//
//            }
//
//            @Override
//            public void onRewarded(RewardItem rewardItem) {
//
//            }
//
//            @Override
//            public void onRewardedVideoAdLeftApplication() {
//
//            }
//
//            @Override
//            public void onRewardedVideoAdFailedToLoad(int i) {
//
//            }
//
//            @Override
//            public void onRewardedVideoCompleted() {
//             //   floatingActionButton1.setVisibility(View.INVISIBLE);
//
//
//            }
//        });


        // rewared add end --------------------------------->

    }

    private void showingInterstitialAdd() {

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");  // add unit id for the interstitial add
        final AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);

        interstitialAd.setAdListener(new AdListener(){
            public void onAdLoaded() {
               // floatingActionButton.setVisibility(View.INVISIBLE);

                                interstitialAd.show();


                conditionMatched = true;
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int addView  = 1;
                        Toast.makeText(getApplicationContext(), Integer.toString(addClicked), Toast.LENGTH_SHORT).show();// Code to be executed when the user has left the app.
                        addDataToFireBase(0,addView);
                        conditionMatched = true;
                    }
                });


            }

            @Override
            public void onAdLeftApplication() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(conditionMatched) {
                            addClicked = 1;
                            Toast.makeText(getApplicationContext(), Integer.toString(addClicked), Toast.LENGTH_SHORT).show();// Code to be executed when the user has left the app.
                            addDataToFireBase(addClicked,0);
                            conditionMatched = false;
                        }
                    }
                });

            }

            @Override
            public void onAdClosed() {
               // floatingActionButton.setVisibility(View.INVISIBLE);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(120000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                interstitialAd.loadAd(adRequest1);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                };

                Thread thread = new Thread(runnable);
                thread.start();

            }
        });

    }

    private void addDataToFireBase(int totalAddClicked,int totalView) {
        boolean res = sharedPreferences.getBoolean("firstTimeclick",true);
        if(res){
            String Key = databaseReference.push().getKey();
            Object_Created object_created = new Object_Created(Email,totalAddClicked,Key,totalView);
            databaseReference.child(Key).setValue(object_created);
            editor.putBoolean("firstTimeclick",false);
            editor.commit();

            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("UserKey",Key);
            editor.commit();

        }else {
           String Key = sharedPreferences1.getString("UserKey","user");
            Log.d("UserID",Key);
           Toast.makeText(getApplicationContext(),Key,Toast.LENGTH_SHORT).show();

           int addClicked = sharedPreferences2.getInt("addClicked",1);
           addClicked += totalAddClicked;

            int view = sharedPreferences2.getInt("addView",1);
            view += totalView;


           SharedPreferences.Editor editor = sharedPreferences2.edit();
           editor.putInt("addClicked",addClicked);
           editor.putInt("addView",view);
           editor.commit();
           Toast.makeText(getApplicationContext(),Integer.toString(addClicked),Toast.LENGTH_SHORT).show();
           Log.d("TotalClicked",Integer.toString(addClicked));

           Object_Created object_created = new Object_Created(Email,addClicked,Key,view);
           databaseReference.child(Key).setValue(object_created);
        }
    }

//    public void  loadInterstitialAdd(){
//        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
//    }


//   public void loadRewaredVideo(){
//       rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",new AdRequest.Builder().build());
//    }

    private void init_variables() {
        url = "https://www.google.com/";
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");   //add id for the registered app
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        addClicked = 0;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admob_details");
        sharedPreferences = getSharedPreferences("userStatus_",MODE_PRIVATE);
        sharedPreferences1 = getSharedPreferences("user",MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("click",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        conditionMatched = true;
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
