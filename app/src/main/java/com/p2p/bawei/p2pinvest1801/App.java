package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        CacheManager.getInstance().init(this);
        //使用leakcannary来检测当前应用是否有页面内存泄漏.
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            refWatcher = LeakCanary.install(this);
        }
    }
}
