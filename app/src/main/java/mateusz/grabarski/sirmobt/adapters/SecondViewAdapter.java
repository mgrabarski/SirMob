package mateusz.grabarski.sirmobt.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mateusz.grabarski.sirmobt.R;
import mateusz.grabarski.sirmobt.models.MainItem;

import static mateusz.grabarski.sirmobt.utils.DataGenerator.NUMBER_OF_PRE_DEFINE_ELEMENTS;

/**
 * Created by MGrabarski on 02.12.2017.
 */

public class SecondViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int FIRST_ELEMENT = 0;
    public static final int SECOND_ELEMENT = 1;
    public static final int THIRD_ELEMENT = 2;
    public static final int NORMAL_ELEMENT = 3;

    private List<MainItem> mItems;

    public SecondViewAdapter(List<MainItem> mItems) {
        this.mItems = mItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == FIRST_ELEMENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_element, parent, false);
            viewHolder = new FirstElementViewHolder(view);
        } else if (viewType == SECOND_ELEMENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second_element, parent, false);
            viewHolder = new SecondElementViewHolder(view);
        } else if (viewType == THIRD_ELEMENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_third_element, parent, false);
            viewHolder = new ThirdElementViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            viewHolder = new MainViewAdapter.ItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FirstElementViewHolder)
            ((FirstElementViewHolder) holder).populate();
        else if (holder instanceof SecondElementViewHolder)
            ((SecondElementViewHolder) holder).populate();
        else if (holder instanceof ThirdElementViewHolder)
            ((ThirdElementViewHolder) holder).populate();
        else
            ((MainViewAdapter.ItemViewHolder) holder).populate(mItems.get(position - NUMBER_OF_PRE_DEFINE_ELEMENTS), position);
    }

    @Override
    public int getItemCount() {
        return mItems.size() + NUMBER_OF_PRE_DEFINE_ELEMENTS;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == FIRST_ELEMENT)
            return FIRST_ELEMENT;
        else if (position == SECOND_ELEMENT)
            return SECOND_ELEMENT;
        else if (position == THIRD_ELEMENT)
            return THIRD_ELEMENT;
        else
            return NORMAL_ELEMENT;
    }

    public static class FirstElementViewHolder extends RecyclerView.ViewHolder {

        public FirstElementViewHolder(View itemView) {
            super(itemView);
        }

        public void populate() {
        }
    }

    public static class SecondElementViewHolder extends RecyclerView.ViewHolder {

        public SecondElementViewHolder(View itemView) {
            super(itemView);
        }

        public void populate() {
        }
    }

    public static class ThirdElementViewHolder extends RecyclerView.ViewHolder {

        public ThirdElementViewHolder(View itemView) {
            super(itemView);
        }

        public void populate() {
        }
    }
}
