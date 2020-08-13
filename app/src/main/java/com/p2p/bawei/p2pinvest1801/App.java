package com.p2p.bawei.p2pinvest1801;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    public static Context context;//需要使用的上下文对象：application实例

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }
}
