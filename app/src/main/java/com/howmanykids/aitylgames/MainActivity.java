package com.howmanykids.aitylgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.howmanykids.aitylgames.databinding.ActivityMainBinding;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private int randomNumber;

    private AdView adView;
    private InterstitialAd interstitialAd;
    InterstitialAdListener interstitialAdListener;

    String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle(getString(R.string.app_name));

        interstitialAd = new InterstitialAd(this, "2761142634195398_2761143954195266");

        /*MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);*/

        binding.btnResult.setOnClickListener(this);
        loadBannerAds();


        interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };
    }

    @Override
    public void onClick(View view) {
//        AdSettings.addTestDevice("e5640f61-8a8a-4239-8bad-df9e63e1574c");
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

        if (TextUtils.isEmpty(binding.tieName.getEditableText().toString()) && TextUtils.isEmpty(binding.tieSpouseName.getEditableText().toString())){
            Snackbar.make(view, "Fields can not be empty", Snackbar.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(binding.tieName.getEditableText().toString())) {
            Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(binding.tieSpouseName.getEditableText().toString())){
            Toast.makeText(this, "Please fill your spouse name", Toast.LENGTH_SHORT).show();
            return;
        }else {
            randomNumber = new Util().generateRandomNumber();
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra(Keys.HUSBAND_NAME, binding.tieName.getEditableText().toString());
            intent.putExtra(Keys.SPOUSE_NAME, binding.tieSpouseName.getEditableText().toString());
            intent.putExtra(Keys.RANDOM_NUMBER, String.valueOf(randomNumber));
            startActivity(intent);
        }
    }

    private void loadBannerAds() {

        // Instantiate an AdView object.
        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // for test ads, you can add IMG_16_9_APP_INSTALL# to your placement id. When your app is ready to serve real ads remove IMG_16_9_APP_INSTALL#.
        adView = new AdView(MainActivity.this, "IMG_16_9_APP_INSTALL#2761142634195398_2761143684195293", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();
    }
}