package com.example.win10.mvpexample.task3.model;

import com.example.win10.mvpexample.task3.content.Counter;

public interface ThirdModel {
    void loadCount();

    void loadCount(int delta, String action);

    interface OnLoadingFinishedListener {
        void onLoadError();

        void onSuccess(Counter counter);
    }
}
