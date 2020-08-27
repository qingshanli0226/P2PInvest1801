package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.example.common.CacheManager;
import com.example.framework.base.manager.UserManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class FinanceApplication extends Application {

    private RefWatcher refWatcher;
    public static FinanceApplication financeApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        financeApplication = this;

//        CrashHandler.getInstance().init(this);
        CacheManager.getInstance().init(this);
        UserManager.getInstance().init(this);

        if(!LeakCanary.isInAnalyzerProcess(this)){
            refWatcher = LeakCanary.install(this);
        }
    }
}
