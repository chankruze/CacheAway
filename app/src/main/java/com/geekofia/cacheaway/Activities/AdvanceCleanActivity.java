package com.geekofia.cacheaway.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.geekofia.cacheaway.R;
import com.geekofia.cacheaway.Utils.Clean;

import static com.geekofia.cacheaway.Utils.CleanCommands.LIST_COMMANDS;

public class AdvanceCleanActivity extends AppCompatActivity implements View.OnClickListener {

    private GridLayout mGridLayout;
    private CardView mDalvikCacheCard, mCacheCard, mAppCacheCard,
            mTempFilesCard, mLogCard, mLoggerCard,
            mDropboxCard, mTombstoneCard, mUsageStatsCard,
            mAnrCard, mLostDir, mThumbnailsCard;

    private ImageButton mClose, mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_clean);

        mGridLayout = findViewById(R.id.gridLayout);

        mClose = findViewById(R.id.AppTitleBarClose);
        mClose.setOnClickListener(this);
        mInfo = findViewById(R.id.AppTitleBarInfo);
        mInfo.setOnClickListener(this);

        initCards();
        setClickListeners();
    }

    private void initCards() {
        mDalvikCacheCard = (CardView) mGridLayout.getChildAt(0);
        mCacheCard = (CardView) mGridLayout.getChildAt(1);
        mAppCacheCard = (CardView) mGridLayout.getChildAt(2);
        mTempFilesCard = (CardView) mGridLayout.getChildAt(3);
        mLogCard = (CardView) mGridLayout.getChildAt(4);
        mLoggerCard = (CardView) mGridLayout.getChildAt(5);
        mDropboxCard = (CardView) mGridLayout.getChildAt(6);
        mTombstoneCard = (CardView) mGridLayout.getChildAt(7);
        mUsageStatsCard = (CardView) mGridLayout.getChildAt(8);
        mAnrCard = (CardView) mGridLayout.getChildAt(9);
        mLostDir = (CardView) mGridLayout.getChildAt(10);
        mThumbnailsCard = (CardView) mGridLayout.getChildAt(11);
    }

    private void setClickListeners() {
        int mChildrenCards = mGridLayout.getChildCount();

        for (int i = 0; i < mChildrenCards; i++) {
            final CardView cardView = (CardView) mGridLayout.getChildAt(i);
            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Clean clean = new Clean(getApplicationContext(), LIST_COMMANDS[finalI], "Done !");
                    clean.CleanCache();
                }
            });
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AppTitleBarClose:
                finish();
                overridePendingTransition(0, android.R.anim.fade_out);
                break;
            case R.id.AppTitleBarInfo:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.show(getSupportFragmentManager(), "App Info");
                break;
        }
    }
}
