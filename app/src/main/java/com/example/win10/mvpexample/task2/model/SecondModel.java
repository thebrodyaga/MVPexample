package com.example.win10.mvpexample.task2.model;

import com.example.win10.mvpexample.task2.content.Date;

public interface SecondModel {
    void loadDate();

    interface OnLoadingFinishedListener {
        void onLoadError();

        void onSuccess(Date list);
    }
}
