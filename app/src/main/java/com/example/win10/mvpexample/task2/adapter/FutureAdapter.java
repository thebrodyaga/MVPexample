package com.example.win10.mvpexample.task2.adapter;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win10.mvpexample.R;
import com.example.win10.mvpexample.task2.FutureFragment.OnListFragmentInteractionListener;
import com.example.win10.mvpexample.task2.content.Future;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.ViewHolder> {

    private final List<Future> mValues;
    private final OnListFragmentInteractionListener mListener;

    public FutureAdapter(List<Future> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    // Clean all elements of the recycler
    public void clear() {
        mValues.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Future> list) {
        mValues.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getDescription());

        if (holder.timer == null) {
            long millis = mValues.get(position).getTime();
            holder.timer = new CountDownTimer(millis, 1000) { // adjust the milli seconds here
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;
                    if (days > 99)
                        days = days % 100;
                    String time = days + ":" + hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
                    holder.mIdView.setText(time);
                }

                public void onFinish() {
                    holder.mIdView.setText("done!");
                }
            }.start();
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Future mItem;
        CountDownTimer timer;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
