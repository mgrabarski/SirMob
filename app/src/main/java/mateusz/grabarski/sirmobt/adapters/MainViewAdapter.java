package mateusz.grabarski.sirmobt.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mateusz.grabarski.sirmobt.R;
import mateusz.grabarski.sirmobt.models.MainItem;

/**
 * Created by MGrabarski on 30.11.2017.
 */

public class MainViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_ITEM = 0;
    private final int VIEW_PROGRESS = 1;

    private List<MainItem> mItems;
    private OnLoadMoreListener onLoadMoreListener;

    public MainViewAdapter(List<MainItem> items) {
        this.mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            viewHolder = new ItemViewHolder(view);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);

            viewHolder = new ProgressViewHolder(v);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder)
            ((ItemViewHolder) holder).populate(mItems.get(position), position);
        else
            ((ProgressViewHolder) holder).populate(true);
    }

    @Override
    public int getItemViewType(int position) {
        return !mItems.get(position).isProgress() ? VIEW_ITEM : VIEW_PROGRESS;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_main_iv)
        ImageView itemMainIv;

        @BindView(R.id.item_main_tv)
        TextView itemMainTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(MainItem mainItem, int position) {
            Picasso.with(itemMainIv.getContext()).load(mainItem.getDrawable()).centerCrop().fit().into(itemMainIv);
            itemMainTv.setText(mainItem.getText() + " " + position);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_progress_bar)
        ProgressBar progressBar;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(boolean progress) {
            progressBar.setIndeterminate(progress);
        }
    }
}
