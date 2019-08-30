package com.geekofia.cacheaway.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.geekofia.cacheaway.R;
import com.geekofia.cacheaway.Utils.Clean;

import hotchemi.android.rate.AppRate;

import static com.geekofia.cacheaway.Utils.CleanCommands.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSimpleClean, mAdvanceClean;
    ImageView mSettings;
    private String CACHE_ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleClean = findViewById(R.id.buttonSimpleClean);
        mAdvanceClean = findViewById(R.id.buttonAdvanceClean);
        mSettings = findViewById(R.id.SettingsButton);


        mSimpleClean.setOnClickListener(this);
        mAdvanceClean.setOnClickListener(this);
        mSettings.setOnClickListener(this);

        CACHE_ALL = CACHE_DALVIK + " | " + CACHE + " | " + CACHE_APP
                + " | " + CACHE_LOGS + " | " + CACHE_LOGGER + " | " + CACHE_USAGE_STATS
                + " | " + CACHE_TEMP_FILES + " | " + CACHE_DROP_BOX + " | " + CACHE_ANR
                + " | " + CACHE_TOMBSTONE + " | " + CACHE_LOST_DIR + " | " + CACHE_THUMBNAILS;

        showRateApp();
    }

    private void showRateApp() {
        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdvanceClean:
                Intent advanceCleanIntent = new Intent(this, AdvanceCleanActivity.class);
                startActivity(advanceCleanIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.buttonSimpleClean:
                Clean clean = new Clean(this, CACHE_ALL, "All cache cleaned");
                clean.CleanCache();
                break;
            case R.id.SettingsButton:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }
}
