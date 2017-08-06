package com.example.win10.mvpexample.task1.view;

import java.util.List;

public interface FirstView {
    void showCounters(List<String> counters);

    void showEmpty();

    void showError();

    void onLoadMore();

}
