package com.geekofia.cacheaway.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.geekofia.cacheaway.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        initViews();
    }

    private void initViews() {
        mClose = findViewById(R.id.settingsTitleBarClose);
        mClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settingsTitleBarClose:
                finish();
                overridePendingTransition(0, android.R.anim.fade_out);
                break;
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat{

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            PreferenceScreen preferenceScreen = getPreferenceScreen();

            Context mContext = preferenceScreen.getContext();
            mContext.setTheme(R.style.SettingsTheme);
        }
    }
}