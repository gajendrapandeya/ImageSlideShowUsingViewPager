package com.codermonkeys.imageslideshowusingviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import com.codermonkeys.imageslideshowusingviewpager.SlideshowAdapter.*;

public class MainActivity extends AppCompatActivity {

    //Ui components
    Toolbar mToolBar;
    ViewPager mViewPager;
    CircleIndicator mCircleIndicator;

    //vars
    SlideshowAdapter mAdapter;
    Handler mHandler;
    Runnable mRunnable;
    Timer mTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mViewPager = findViewById(R.id.view_pager_id);
        mCircleIndicator = findViewById(R.id.circle_indicator_id);

        mAdapter = new SlideshowAdapter(this);

        mViewPager.setAdapter(mAdapter);

        mCircleIndicator.setViewPager(mViewPager);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {

                int currentItemInViewPager = mViewPager.getCurrentItem();

                if(currentItemInViewPager == mAdapter.imagesRes.length-1) {

                    currentItemInViewPager = 0;
                    mViewPager.setCurrentItem(currentItemInViewPager, true);
                } else {
                    currentItemInViewPager++;
                    mViewPager.setCurrentItem(currentItemInViewPager, true);
                }
            }
        };

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(mRunnable);
            }
        }, 4000, 4000);
    }
}
