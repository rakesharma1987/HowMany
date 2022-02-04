package com.howmanykids.aitylgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.howmanykids.aitylgames.databinding.ActivityResultBinding;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityResultBinding binding;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });


        /*AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);*/

        int randomNumber = new Util().generateRandomNumber();

        binding.maleName.setText(getIntent().getStringExtra(Keys.HUSBAND_NAME));
        binding.femaleName.setText(getIntent().getStringExtra(Keys.SPOUSE_NAME));
        binding.maleKids.setText(getIntent().getStringExtra(Keys.RANDOM_NUMBER));
        binding.femaleKids.setText(String.valueOf(randomNumber));

        binding.btnTryAgain.setOnClickListener(this);

        loadBannerAds();
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    private void loadBannerAds() {

        // Instantiate an AdView object.
        // NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
        // for test ads, you can add IMG_16_9_APP_INSTALL# to your placement id. When your app is ready to serve real ads remove IMG_16_9_APP_INSTALL#.
        adView = new AdView(ResultActivity.this, "IMG_16_9_APP_INSTALL#2761142634195398_2761143684195293", AdSize.BANNER_HEIGHT_50);
        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        // Add the ad view to your activity layout
        adContainer.addView(adView);
        // Request an ad
        adView.loadAd();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}