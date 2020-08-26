package com.p2p.bawei.p2pinvest1801;

import android.support.annotation.NonNull;
import android.util.Log;

//捕获未知异常
public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CustomExceptionHandler";
    private Thread.UncaughtExceptionHandler mDefaultUEH;

    public CustomExceptionHandler() {
        Log.d(TAG, "------------ CustomExceptionHandler ------------");
        mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e(TAG, "------------ uncaughtException ------------ " + e.getMessage());
        mDefaultUEH.uncaughtException(t,e);//不加本语句会导致ANR
    }
}
