package com.road.star.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.road.star.base.BaseActivity;
import com.road.star.utils.AppUtils;
import com.road.star.utils.NetworkHandler;

import java.lang.reflect.Method;

public class AppController extends Application implements Application.ActivityLifecycleCallbacks{

    private static AppController instance;
    private BaseActivity activity;
    private static Context appContext;

    /**
     * Gets create.
     *
     * @return the create
     */
    public static synchronized AppController getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NetworkHandler.isConnected = AppUtils.getNetworkState(this);

        registerActivityLifecycleCallbacks(this);
        appContext = this;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return appContext;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof BaseActivity)
            this.activity = (BaseActivity) activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public BaseActivity getActivity() {
        return activity;
    }
}
