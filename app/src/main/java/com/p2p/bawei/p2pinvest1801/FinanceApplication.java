package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class FinanceApplication extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        CacheManager.getInstance().init(this);

        if(!LeakCanary.isInAnalyzerProcess(this)){
            refWatcher = LeakCanary.install(this);
        }
    }
}
