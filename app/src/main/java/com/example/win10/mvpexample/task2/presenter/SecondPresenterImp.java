package com.example.win10.mvpexample.task2.presenter;

import com.example.win10.mvpexample.task2.SecondActivity;
import com.example.win10.mvpexample.task2.content.Date;
import com.example.win10.mvpexample.task2.model.SecondModel;
import com.example.win10.mvpexample.task2.model.SecondModelImp;
import com.example.win10.mvpexample.task2.view.SecondView;


public class SecondPresenterImp implements SecondPresenter, SecondModel.OnLoadingFinishedListener {
    private SecondView secondView;
    private SecondModel secondMode;

    public SecondPresenterImp(SecondView secondView) {
        this.secondView = secondView;
        this.secondMode = new SecondModelImp(this);
    }

    @Override
    public void loadData() {
        secondMode.loadDate();
    }

    @Override
    public void onDestroy() {
        secondView = null;
    }

    @Override
    public void onLoadError() {
        if (secondView != null) {
            secondView.showError();
        }
    }

    @Override
    public void onSuccess(Date date) {
        if (secondView != null) {
            secondView.refresh(date.getPast(), date.getFuture());
        }
    }
}
