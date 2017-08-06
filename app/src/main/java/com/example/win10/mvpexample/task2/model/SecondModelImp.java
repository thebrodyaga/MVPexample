package com.example.win10.mvpexample.task2.model;

import com.example.win10.mvpexample.api.ApiFactory;
import com.example.win10.mvpexample.api.MurzinmaService;
import com.example.win10.mvpexample.task2.content.Date;
import com.example.win10.mvpexample.task2.presenter.SecondPresenterImp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondModelImp implements SecondModel {
    private OnLoadingFinishedListener presenter;

    public SecondModelImp(OnLoadingFinishedListener presenter) {
        this.presenter=presenter;
    }

    @Override

    public void loadDate() {
        MurzinmaService murzinmaService = ApiFactory.getMurzinmaService();
        Call<Date> call = murzinmaService.getDate();
        call.enqueue(new Callback<Date>() {
            @Override
            public void onResponse(Call<Date> call, Response<Date> response) {
                if (response.isSuccessful()) {
                    presenter.onSuccess(response.body());
                } else {
                    presenter.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<Date> call, Throwable t) {
                presenter.onLoadError();
            }
        });
    }
}
