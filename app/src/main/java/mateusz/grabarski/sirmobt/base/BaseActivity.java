package mateusz.grabarski.sirmobt.base;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import mateusz.grabarski.sirmobt.R;

/**
 * Created by MGrabarski on 02.12.2017.
 */

abstract public class BaseActivity extends AppCompatActivity {

    public static final int DOUBLE_BACK_DELAY = 2000;

    boolean doubleBackToExit = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExit = true;
        Toast.makeText(this, R.string.double_tap_to_exit, Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExit = false;
            }
        }, DOUBLE_BACK_DELAY);
    }
}
