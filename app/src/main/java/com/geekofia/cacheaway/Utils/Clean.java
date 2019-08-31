package com.geekofia.cacheaway.Utils;

import android.content.Context;
import android.os.AsyncTask;

import com.sdsmdg.tastytoast.TastyToast;

public class Clean {

    private String mCommand, mToastMessage, mResult;
    private Context mContext;

    public Clean(Context mContext, String mCommand, String mToastMessage) {
        this.mContext = mContext;
        this.mCommand = mCommand;
        this.mToastMessage = mToastMessage;
    }

    public Void CleanCache() {
        new CleaningTask().execute();

        return null;
    }

    private class CleaningTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... param) {
            final String command[] = {"su", "-c", "rm -rf " + mCommand + " | exit"};

            Shell shell = new Shell();
            mResult = shell.sendShellCommand(command);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void param) {
            if (mResult != "") {
                TastyToast.makeText(mContext, mResult, TastyToast.LENGTH_LONG, TastyToast.ERROR);
            } else {
                TastyToast.makeText(mContext, mToastMessage, TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
            }
        }
    }
}
