package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.example.framework2.manager.CacheManager;
import com.p2p.bawei.p2pinvest1801.exception.CrashHandler;

public class App extends Application {
    public static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
//        CrashHandler.getInstance().init(this);
        CacheManager.getInstance().init(this);
    }
}
