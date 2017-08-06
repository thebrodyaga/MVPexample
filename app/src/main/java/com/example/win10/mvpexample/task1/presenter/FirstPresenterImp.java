package com.example.win10.mvpexample.task1.presenter;

import com.example.win10.mvpexample.task1.FirstActivity;
import com.example.win10.mvpexample.task1.model.FirstModel;
import com.example.win10.mvpexample.task1.model.FirstModelImp;

import java.util.ArrayList;
import java.util.List;

public class FirstPresenterImp implements FirstPresenter, FirstModel.OnLoadingFinishedListener {

    private FirstActivity firstActivity;
    private FirstModelImp firstModel;

    private static int page = 0;
    private static List<String> listResult;

    public FirstPresenterImp(FirstActivity firstActivity) {
        this.firstActivity = firstActivity;
        this.firstModel = new FirstModelImp(this, firstActivity);
    }

    @Override
    public void loadData() {
        if (listResult == null)
            listResult = new ArrayList<>();
        firstModel.loadList(page);
    }

    @Override
    public void onDestroy() {
        firstActivity = null;
        page=0;
    }

    @Override
    public void onLoadError() {
        if (firstActivity != null) {
            if (listResult.isEmpty())
                firstActivity.showEmpty();
            else firstActivity.showError();
        }
    }

    @Override
    public void onSuccess(List<String> list) {
        if (firstActivity != null) {
            listResult = list;
            page = page + 1;
            firstActivity.showCounters(listResult);
        }
    }
}
