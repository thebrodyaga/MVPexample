package com.example.win10.mvpexample.task3.model;

import com.example.win10.mvpexample.api.ApiFactory;
import com.example.win10.mvpexample.api.MurzinmaService;
import com.example.win10.mvpexample.task3.content.Counter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdModelImp implements ThirdModel {
    private OnLoadingFinishedListener presenter;
    private MurzinmaService murzinmaService;

    public ThirdModelImp(OnLoadingFinishedListener presenter) {
        this.presenter = presenter;
        murzinmaService = ApiFactory.getMurzinmaService();
    }

    @Override
    public void loadCount() {
        Call<Counter> call = murzinmaService.getCount();
        call.enqueue(new Callback<Counter>() {
            @Override
            public void onResponse(Call<Counter> call, Response<Counter> response) {
                if (response.isSuccessful()) {
                    presenter.onSuccess(response.body());
                } else {
                    presenter.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<Counter> call, Throwable t) {
                presenter.onLoadError();
            }
        });
    }

    @Override
    public void loadCount(int delta, String action) {
        Call<Counter> call = murzinmaService.getCount(delta,action);
        call.enqueue(new Callback<Counter>() {
            @Override
            public void onResponse(Call<Counter> call, Response<Counter> response) {
                if (response.isSuccessful()) {
                    presenter.onSuccess(response.body());
                } else {
                    presenter.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<Counter> call, Throwable t) {
                presenter.onLoadError();
            }
        });
    }
}
