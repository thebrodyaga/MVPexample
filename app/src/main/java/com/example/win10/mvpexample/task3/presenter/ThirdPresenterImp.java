package com.example.win10.mvpexample.task3.presenter;

import com.example.win10.mvpexample.task3.ThirdActivity;
import com.example.win10.mvpexample.task3.content.Counter;
import com.example.win10.mvpexample.task3.model.ThirdModel;
import com.example.win10.mvpexample.task3.model.ThirdModelImp;
import com.example.win10.mvpexample.task3.view.ThirdView;

import java.util.Timer;
import java.util.TimerTask;

public class ThirdPresenterImp implements ThirdPresenter, ThirdModel.OnLoadingFinishedListener {
    private ThirdView thirdView;
    private ThirdModel model;
    private int delta = 0;
    private Timer mTimer;

    public ThirdPresenterImp(ThirdActivity thirdActivity) {
        this.thirdView = thirdActivity;
        model = new ThirdModelImp(this);
    }

    @Override
    public void plusClick() {
        delta = delta + 1;
        getCount();
    }

    @Override
    public void minusClick() {
        delta = delta - 1;
        getCount();
    }

    public void getCount() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (delta == 0)
            model.loadCount();
        else {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (delta > 0)
                        model.loadCount(delta, "increment");
                    if (delta < 0)
                        model.loadCount(Math.abs(delta), "decrement");
                    if (delta == 0)
                        model.loadCount();
                    delta = 0;
                }
            }, 1000);
        }
    }

    @Override
    public void onDestroy() {
        thirdView = null;
    }

    @Override
    public void onLoadError() {
        if (thirdView != null) {
            thirdView.showError();
        }
    }

    @Override
    public void onSuccess(Counter counter) {
        if (thirdView != null) {
            thirdView.refresh(counter.getValue());
        }
    }
}
