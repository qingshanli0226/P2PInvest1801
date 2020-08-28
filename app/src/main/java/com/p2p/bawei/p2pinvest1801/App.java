package com.p2p.bawei.p2pinvest1801;

import android.app.Application;

import com.bw.net.SpManager;
import com.p2p.bawei.p2pinvest1801.cacheError.CacheError;

public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;

        SpManager.getInstance().getSp(this,"Tokens");
        UserManager.getInstance().init(this);

        CacheError.getInstance().init(this);
        APKVersionCodeUtils.getInstance().init(this);
    }


}
