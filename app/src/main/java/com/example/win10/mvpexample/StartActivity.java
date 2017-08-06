package com.example.win10.mvpexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.win10.mvpexample.task1.FirstActivity;
import com.example.win10.mvpexample.task2.SecondActivity;
import com.example.win10.mvpexample.task3.ThirdActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        (findViewById(R.id.button)).setOnClickListener(this);
        (findViewById(R.id.button2)).setOnClickListener(this);
        (findViewById(R.id.button3)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                startActivity(new Intent(this, FirstActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ThirdActivity.class));
                break;

        }
    }
}
