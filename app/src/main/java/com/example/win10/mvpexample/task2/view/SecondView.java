package com.example.win10.mvpexample.task2.view;


import com.example.win10.mvpexample.task2.content.Future;
import com.example.win10.mvpexample.task2.content.Past;

import java.util.List;

public interface SecondView {
    void refresh(List<Past> pastList,List<Future> futureList);

    void showError();
}
