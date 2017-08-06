package com.example.win10.mvpexample.task1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.win10.mvpexample.R;
import com.example.win10.mvpexample.task1.view.FirstView;

import java.util.List;

public class CounterAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<String> listCities;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private FirstView firstView;


    public CounterAdapter(List<String> counters) {
        listCities = counters;
        setHasStableIds(true);
    }

    @Override
    public int getItemViewType(int position) {
        return listCities.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setCounterList(List<String> listCities) {
        this.listCities = listCities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.counter_row, parent, false);

            vh = new CitiesViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_loading, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CitiesViewHolder) {

            String singleCounter = (String) listCities.get(position);

            ((CitiesViewHolder) holder).tvText.setText(singleCounter);

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return listCities.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnLoadMoreListener(FirstView firstView) {
        this.firstView = firstView;
    }


    //
    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        public TextView tvText;

        public CitiesViewHolder(View v) {
            super(v);
            tvText = (TextView) v.findViewById(R.id.counter_text);

        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

}