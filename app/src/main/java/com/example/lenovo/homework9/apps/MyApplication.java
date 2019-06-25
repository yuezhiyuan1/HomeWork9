package com.example.lenovo.homework9.apps;

import android.app.Application;

/**
 * Created by Lenovo on 2019/6/3.
 */

public class MyApplication extends Application {

    private static Application MyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication = this;
    }

    public static Application getApplication() {
        return MyApplication;
    }
}
