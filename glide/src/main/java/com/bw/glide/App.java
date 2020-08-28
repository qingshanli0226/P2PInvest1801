package com.bw.glide;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlideManager.getInstance().init(this);
    }
}
