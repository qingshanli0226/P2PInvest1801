package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.p2p.bawei.p2pinvest1801.cache.CacheManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CacheManager.getInstance().init(this);
    }
}
