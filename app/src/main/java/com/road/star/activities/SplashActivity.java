package com.road.star.activities;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;

import com.road.star.R;
import com.road.star.base.BaseActivity;
import com.road.star.databinding.ActivitySplashBinding;
import com.road.star.utils.Constants;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding activitySplashBinding;

    private Runnable runnable = () -> {
        WelcomeActivity.startActivity(this);
        finish();
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        moveToNext();
    }

    private void moveToNext() {
        Handler handler = new Handler();
        handler.postDelayed(runnable, Constants.SPLASH_DURATION);
    }
}
