package mateusz.grabarski.sirmobt.utils;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import mateusz.grabarski.sirmobt.dialogs.ProgressDialog;

/**
 * Created by MGrabarski on 30.11.2017.
 */

public class ProgressAsyncTask extends AsyncTask<Void, Integer, Void> {

    private ProgressDialog mProgressDialog;
    private final PressListener mListener;
    private int mSeconds;
    private int mProgress;

    public ProgressAsyncTask(ProgressDialog dialog, int seconds, PressListener listener) {
        this.mSeconds = seconds;
        this.mListener = listener;
        mProgress = 0;
        this.mProgressDialog = dialog;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (int i = 0; i < mSeconds; i++)
            try {
                Thread.sleep(1000);
                publishProgress(20);
            } catch (InterruptedException e) {
            }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgress += values[0];

        mProgressDialog.updateProgress(mProgress);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mProgressDialog.dismiss();
        mListener.onProgressFinish();
    }

    public void attach(ProgressDialog progressDialog) {
        this.mProgressDialog = progressDialog;
    }

    public void detach() {
        mProgressDialog = null;
    }

    public interface PressListener {
        void onProgressFinish();
    }
}
