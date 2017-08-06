package com.example.win10.mvpexample.task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.win10.mvpexample.R;
import com.example.win10.mvpexample.task3.presenter.ThirdPresenter;
import com.example.win10.mvpexample.task3.presenter.ThirdPresenterImp;
import com.example.win10.mvpexample.task3.view.ThirdView;

public class ThirdActivity extends AppCompatActivity implements ThirdView, View.OnClickListener {
    TextView tvCount;
    Button buttonMinus;
    Button buttonPlus;
    ThirdPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tvCount = (TextView) findViewById(R.id.tvCount);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        presenter = new ThirdPresenterImp(this);
        presenter.getCount();
    }

    @Override
    public void refresh(int count) {
        tvCount.setText(String.valueOf(count));
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonMinus:
                presenter.minusClick();
                break;
            case R.id.buttonPlus:
                presenter.plusClick();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
