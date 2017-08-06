package com.example.win10.mvpexample.task1;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.example.win10.mvpexample.R;
import com.example.win10.mvpexample.task1.adapter.CounterAdapter;
import com.example.win10.mvpexample.task1.presenter.FirstPresenterImp;
import com.example.win10.mvpexample.task1.view.FirstView;

import java.util.ArrayList;
import java.util.List;


public class FirstActivity extends AppCompatActivity implements FirstView, View.OnClickListener {

    private static final int POSITION_LIST = 0;
    private static final int POSITION_EMPTY = 2;
    private static final String LIST_STATE_KEY = "listKey";

    private LinearLayoutManager layoutManager;
    private ViewAnimator animator;
    private RecyclerView recyclerView;
    private CounterAdapter adapter;
    private FirstPresenterImp presenter;
    private Button button;

    private List<String> listCities;
    private boolean isLoading = false;
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        animator = (ViewAnimator) findViewById(R.id.animator);
        recyclerView = (RecyclerView) animator.getChildAt(POSITION_LIST);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new FirstPresenterImp(this);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int visibleThreshold = 5;
                //Log.d("MyLog", "lastVisibleItem = " + lastVisibleItem + "totalItemCount = " + totalItemCount +" isLoading = " + isLoading);
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    isLoading = true;
                    onLoadMore();
                }
            }
        });

        listCities = new ArrayList<>();
        adapter = new CounterAdapter(listCities);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this);
        presenter.loadData();
    }

    @Override
    public void showCounters(List<String> list) {
        if (!(listCities.isEmpty())) {
            listCities.remove(listCities.size() - 1);
            adapter.notifyItemRemoved(listCities.size());
        }
        listCities.addAll(list);
        isLoading = false;
        adapter.notifyDataSetChanged();
        animator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void showEmpty() {
        animator.setDisplayedChild(POSITION_EMPTY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mListState != null) {
            layoutManager.onRestoreInstanceState(mListState);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showError() {
        if (!(listCities.isEmpty())) {
            listCities.remove(listCities.size() - 1);
            adapter.notifyItemRemoved(listCities.size());
            isLoading = false;
        }
        Toast.makeText(this, R.string.error_s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if (!(listCities.isEmpty())) {
            listCities.add(null);
            adapter.notifyItemInserted(listCities.size() - 1);
        }
        presenter.loadData();
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        // Save list state
        mListState = layoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, mListState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);

        // Retrieve list state and list/item positions
        if(state != null)
            mListState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    public void onClick(View v) {
        onLoadMore();
    }
}
