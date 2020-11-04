package com.lcz.utilslib;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp sBaseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseApp = this;

    }

    public static BaseApp getInstance() {
        return sBaseApp;
    }

}