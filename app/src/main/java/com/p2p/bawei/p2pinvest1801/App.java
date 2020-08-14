package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
