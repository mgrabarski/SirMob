package mateusz.grabarski.sirmobt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mateusz.grabarski.sirmobt.adapters.MainViewAdapter;
import mateusz.grabarski.sirmobt.base.BaseActivity;
import mateusz.grabarski.sirmobt.dialogs.ProgressDialog;
import mateusz.grabarski.sirmobt.models.MainItem;
import mateusz.grabarski.sirmobt.utils.DataGenerator;
import mateusz.grabarski.sirmobt.utils.ProgressAsyncTask;

import static mateusz.grabarski.sirmobt.Constants.ASYNC_TASK_WORKING_TIME;
import static mateusz.grabarski.sirmobt.Constants.MAX_NUMBER_OF_ELEMENTS;

public class MainActivity extends BaseActivity implements ProgressDialog.ProgressDialogListener, ProgressAsyncTask.PressListener {

    @BindView(R.id.activity_main_author_text)
    TextView authorTv;

    @BindView(R.id.activity_main_rv)
    RecyclerView list;

    private ProgressAsyncTask mProgressAsyncTask;
    private ProgressDialog mProgressDialog;
    private List<MainItem> mItems;
    private MainViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mProgressDialog = ProgressDialog.newInstance();

        if (mProgressAsyncTask == null) {
            mProgressAsyncTask = new ProgressAsyncTask(mProgressDialog, 5, this);
        } else {
            mProgressAsyncTask.attach(mProgressDialog);
        }

        mLayoutManager = new LinearLayoutManager(this);

        mItems = DataGenerator.getMainViewData();
        mAdapter = new MainViewAdapter(mItems);

        list.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(MainActivity.class.getSimpleName(), "onSwiped: " + direction + ", v = " + viewHolder.getAdapterPosition());
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(list);

        list.setLayoutManager(mLayoutManager);


        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (totalItemCount == MAX_NUMBER_OF_ELEMENTS)
                        return;

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;

                            mItems.add(new MainItem("Item", 0, true));
                            mAdapter.notifyDataSetChanged();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mItems.remove(mItems.size() - 1);
                                    mItems.addAll(DataGenerator.getLoadedItems());
                                    mAdapter.notifyDataSetChanged();
                                    loading = true;
                                }
                            }, 5000);
                        }
                    }
                }
            }
        });
    }

    @OnClick(R.id.activity_main_author_text)
    public void onAuthorClick() {
        mProgressAsyncTask = new ProgressAsyncTask(mProgressDialog, ASYNC_TASK_WORKING_TIME, this);
        mProgressDialog.show(getSupportFragmentManager(), null);
        mProgressDialog.updateProgress(0);
        mProgressAsyncTask.execute();
        lockScreenOrientation(true);
        authorTv.setTextColor(getResources().getColor(android.R.color.white));
    }

    @Override
    public void onCancelDialog() {
        mProgressAsyncTask.cancel(true);
        authorTv.setTextColor(getResources().getColor(android.R.color.black));
        lockScreenOrientation(false);
    }

    @Override
    public void onProgressFinish() {
        authorTv.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        lockScreenOrientation(false);
    }
}
