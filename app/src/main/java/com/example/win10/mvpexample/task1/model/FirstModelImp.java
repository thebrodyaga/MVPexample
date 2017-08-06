package com.example.win10.mvpexample.task1.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.win10.mvpexample.api.ApiFactory;
import com.example.win10.mvpexample.api.MurzinmaService;
import com.example.win10.mvpexample.task1.FirstActivity;
import com.example.win10.mvpexample.task1.content.Cities;
import com.example.win10.mvpexample.task1.database.DataBaseHelper;
import com.example.win10.mvpexample.task1.presenter.FirstPresenterImp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstModelImp implements FirstModel {
    private FirstPresenterImp presenter;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public FirstModelImp(FirstPresenterImp presenter, FirstActivity firstActivity) {
        this.presenter = presenter;
        dataBaseHelper = new DataBaseHelper(firstActivity);
    }

    @Override
    public void loadList(int page) {
        db = dataBaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM MyTable WHERE PAGE = ?", new String[]{String.valueOf(page)});

        if (cursor.moveToFirst())
            loadFromBD(page);
        else {
            cursor.close();
            db.close();
            loadFromNet(page);
        }

    }

    @Override
    public void loadFromNet(int page) {
        Log.d("MyLog","loadFromNet");
        MurzinmaService murzinmaService = ApiFactory.getMurzinmaService();
        Call<Cities> call = murzinmaService.getCities(page);
        call.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.isSuccessful()) {
                    saveInDataBase(response.body());
                    presenter.onSuccess(response.body().getCities());
                } else {
                    presenter.onLoadError();
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                presenter.onLoadError();
            }
        });
    }

    @Override
    public void loadFromBD(int page) {
        Log.d("MyLog","loadFromBD");
        List<String> list = new ArrayList<>();
        do {
            list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.Columns.CITY_NAME)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        presenter.onSuccess(list);
    }

    private void saveInDataBase(@NonNull Cities cities) {
        Log.d("MyLog","saveInDataBase");
        List<String> list = cities.getCities();
        int page = cities.getPage();
        db = dataBaseHelper.getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            insertDB(list.get(i), page);
        }
        db.close();

    }

    @NonNull
    private void insertDB(@NonNull String city, int page) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.Columns.PAGE, page);
        values.put(DataBaseHelper.Columns.CITY_NAME, city);
        db.insert(DataBaseHelper.Requests.TABLE_NAME, null, values);
    }
}
