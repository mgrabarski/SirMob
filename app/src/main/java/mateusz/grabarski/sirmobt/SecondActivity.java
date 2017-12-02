package mateusz.grabarski.sirmobt;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.sirmobt.adapters.SecondViewAdapter;
import mateusz.grabarski.sirmobt.utils.DataGenerator;

public class SecondActivity extends AppCompatActivity implements SecondViewAdapter.SecondViewAdapterListener {

    @BindView(R.id.activity_second_rv)
    RecyclerView list;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        list.setLayoutManager(new LinearLayoutManager(this));

        SecondViewAdapter adapter = new SecondViewAdapter(DataGenerator.getItemsForSecondView(), this);
        list.setAdapter(adapter);
    }

    @Override
    public void onClickFirstBtnFromFirstElement() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.btn_1)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onClickBtnFromSecondElement() {

        setupProgressDialog();

        Thread thread = new Thread() {
            @Override
            public void run() {
                int progress = 0;
                for (int i = 0; i < 5; i++)
                    try {
                        Thread.sleep(1000);
                        progress += 20;
                        loadProgress(progress);
                    } catch (InterruptedException e) {
                    }

                mProgressDialog.dismiss();
            }
        };

        thread.start();
        mProgressDialog.show();
    }

    private void loadProgress(int progress) {
        if (mProgressDialog == null) {
            setupProgressDialog();
            mProgressDialog.show();
            mProgressDialog.setProgress(progress);
        } else {
            mProgressDialog.setProgress(progress);
        }
    }

    private void setupProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
    }
}
