package com.example.win10.mvpexample.api;

import com.example.win10.mvpexample.task1.content.Cities;
import com.example.win10.mvpexample.task2.content.Date;
import com.example.win10.mvpexample.task3.content.Counter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MurzinmaService {
    @GET("/api/cities.php")
    Call<Cities> getCities(@Query("page") int page);

    @GET("/api/dates.php")
    Call<Date> getDate();

    @GET("/api/counter.php")
    Call<Counter> getCount();

    @GET("/api/counter.php")
    Call<Counter> getCount(@Query("delta") int delta, @Query("action") String action);

}
