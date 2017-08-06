package com.example.win10.mvpexample.task1.model;

import android.database.Cursor;

import com.example.win10.mvpexample.task1.presenter.FirstPresenterImp;

import java.util.List;

public interface FirstModel {
    void loadList(int page);

    void loadFromNet(int page);

    void loadFromBD(int page);



    interface OnLoadingFinishedListener {
        void onLoadError();

        void onSuccess(List<String> list);
    }
}
