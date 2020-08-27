package com.p2p.bawei.p2pinvest1801;

import android.app.Application;
import android.content.Context;

import com.p2p.bawei.p2pinvest1801.exception.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {
    private RefWatcher refWatcher;
    public static App instace;
    @Override
    public void onCreate() {
        super.onCreate();
        instace = this;
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }
        CrashHandler.getInstance().init(this);
    }
}
