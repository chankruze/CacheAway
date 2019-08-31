package com.geekofia.cacheaway.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekofia.cacheaway.R;
import com.geekofia.cacheaway.Utils.Clean;

import java.util.Iterator;
import java.util.Set;

import hotchemi.android.rate.AppRate;

import static com.geekofia.cacheaway.Utils.CleanCommands.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSimpleClean, mAdvanceClean;
    private ImageView mSettings;
    private TextView mTextView;
    private String CACHE_ALL, mToastQuickClean, CHANNEL_ID = "CacheAway";
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleClean = findViewById(R.id.buttonSimpleClean);
        mAdvanceClean = findViewById(R.id.buttonAdvanceClean);
        mSettings = findViewById(R.id.SettingsButton);
        mTextView = findViewById(R.id.textView);

        mSimpleClean.setOnClickListener(this);
        mAdvanceClean.setOnClickListener(this);
        mSettings.setOnClickListener(this);

        createNotificationChannel();
        getSettingsPref();
        showRateApp();
    }

    private void getSettingsPref() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean mCustomizeCleanAll = sharedPrefs.getBoolean(getString(R.string.customize_clean_all_key), false);
        Boolean mNotify = sharedPrefs.getBoolean(getString(R.string.notification_pref_key), true);

        if (mCustomizeCleanAll) {
            Set<String> selections = sharedPrefs.getStringSet(getString(R.string.select_caches_key), null);

            if (selections != null && !selections.isEmpty()) {
                mTextView.setText(getString(R.string.quick_clean_customized));
                mToastQuickClean = getString(R.string.quick_clean_toast_customized);
                cleanPreferredCaches(selections);
            } else {
                mTextView.setText(getString(R.string.quick_clean));
                mToastQuickClean = getString(R.string.quick_clean_toast_default);
                cleanDefaultCaches();
                Toast.makeText(this, "You should choose at least one cache or disable customize clean all in settings", Toast.LENGTH_LONG).show();
            }
        } else {
            if (mNotify) {
                showNotification();
            }
            cleanDefaultCaches();
            mTextView.setText(getString(R.string.quick_clean));
            mToastQuickClean = getString(R.string.quick_clean_toast_default);
        }
    }

    private void cleanPreferredCaches(Set mSet) {
        CACHE_ALL = "";
        Iterator iterator = mSet.iterator();
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            CACHE_ALL += LIST_COMMANDS[Integer.parseInt(element)] + " ";
        }
    }

    private void cleanDefaultCaches() {
        CACHE_ALL = "";
        for (int j = 0; j < LIST_COMMANDS.length; j++) {
            CACHE_ALL += LIST_COMMANDS[j] + " ";
        }
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        Intent configureIntent = new Intent(this, SettingsActivity.class);
        configureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, configureIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_delete)
                .setContentTitle(getString(R.string.notificationTitle))
                .setContentText(getString(R.string.notificationContent))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.notificationContent)))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_settings, getString(R.string.configure),
                        pendingIntent)
                .setAutoCancel(true)
                .setColor(Color.RED);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, builder.build());

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
                Clean clean = new Clean(this, CACHE_ALL, mToastQuickClean);
                clean.CleanCache();
                break;
            case R.id.SettingsButton:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSettingsPref();
    }
}
