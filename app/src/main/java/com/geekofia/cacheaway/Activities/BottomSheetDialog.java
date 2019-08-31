package com.geekofia.cacheaway.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geekofia.cacheaway.BuildConfig;
import com.geekofia.cacheaway.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sdsmdg.tastytoast.TastyToast;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private ImageButton mSocialWeb, mSocialGithub, mSocialMail, mSocialShare;
    private TextView mAppVersionText;

    private String mAppVersion = BuildConfig.VERSION_NAME;
    private String mAppPackage = BuildConfig.APPLICATION_ID;
    private static final String SOCIAL_WEB = "https://geekofia.in/CacheAway", SOCIAL_GITHUB = "https://github.com/geekofia";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet, container, false);

        initButtons(v);

        return v;
    }

    private void initButtons(View view) {
        mSocialWeb = view.getRootView().findViewById(R.id.socialWeb);
        mSocialGithub = view.getRootView().findViewById(R.id.socialGithub);
        mSocialMail = view.getRootView().findViewById(R.id.socialMail);
        mSocialShare = view.getRootView().findViewById(R.id.socialShare);

        mAppVersionText = view.getRootView().findViewById(R.id.appVersionText);
        mAppVersionText.setText("v" + mAppVersion);

        mSocialWeb.setOnClickListener(this);
        mSocialGithub.setOnClickListener(this);
        mSocialMail.setOnClickListener(this);
        mSocialShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.socialWeb:
                Uri webUri = Uri.parse(SOCIAL_WEB);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, webUri);
                startActivity(websiteIntent);
                break;
            case R.id.socialGithub:
                Uri githubUri = Uri.parse(SOCIAL_GITHUB);
                Intent githubIntent = new Intent(Intent.ACTION_VIEW, githubUri);
                startActivity(githubIntent);
                break;
            case R.id.socialMail:
                sendMail();
                break;
            case R.id.socialShare:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing " + getString(R.string.app_name) + " App");
                i.putExtra(Intent.EXTRA_TEXT, "Checkout this cool app of Geekofia\n" + "https://play.google.com/store/apps/details?id=" + mAppPackage);
                startActivity(Intent.createChooser(i, "Sharing " + getString(R.string.app_name) + " App"));
                break;
        }
    }

    private void sendMail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact@geekofia.in"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting Regarding " + getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, there\nWrite Your issues/querries/suggestions ... ");
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Choose an Email client"));
        } catch (ActivityNotFoundException e) {
            TastyToast.makeText(getActivity(), "There is no Email client installed", TastyToast.LENGTH_SHORT, TastyToast.ERROR).show();
            e.printStackTrace();
        }

    }
}
