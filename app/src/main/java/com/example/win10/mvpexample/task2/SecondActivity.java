package com.example.win10.mvpexample.task2;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.win10.mvpexample.R;
import com.example.win10.mvpexample.task2.content.Future;
import com.example.win10.mvpexample.task2.content.Past;
import com.example.win10.mvpexample.task2.presenter.SecondPresenter;
import com.example.win10.mvpexample.task2.presenter.SecondPresenterImp;
import com.example.win10.mvpexample.task2.view.SecondView;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements
        PastFragment.OnListFragmentInteractionListener,
        FutureFragment.OnListFragmentInteractionListener,
        SecondView, SwipeRefreshLayout.OnRefreshListener {

    private PastFragment pastFragment;
    private FutureFragment futureFragment;
    private SecondPresenter presenter;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(this);

        presenter = new SecondPresenterImp(this);
        presenter.loadData();
    }


    @Override
    public void refresh(List<Past> pastList, List<Future> futureList) {
        swipeContainer.setRefreshing(false);
        futureFragment.refreshAdapter(futureList);
        pastFragment.refreshAdapter(pastList);
    }

    @Override
    public void showError() {
        swipeContainer.setRefreshing(false);
        Toast.makeText(this, R.string.error_s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentInteraction(Past item) {

    }

    @Override
    public void onListFragmentInteraction(Future item) {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        presenter.loadData();
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return pastFragment = new PastFragment();
                case 1:
                default:
                    return futureFragment = new FutureFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Past";
                case 1:
                default:
                    return "Future";
            }
        }
    }
}
