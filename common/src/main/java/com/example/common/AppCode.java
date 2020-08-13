package com.example.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AppCode {
    private static Context context;
    private static volatile AppCode appCode;

    public static AppCode getInstance() {
        if (appCode == null) {
            appCode = new AppCode(context);
        }
        return appCode;
    }

    public AppCode(Context context) {
        this.context = context;
    }

    public String getVersion() {

        try {

            PackageManager manager = context.getPackageManager();

            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

            String version = info.versionName;

            return "版本：" + version;

        } catch (Exception e) {
            Log.e("hq", "getVersion: "+e.getMessage() );
            e.printStackTrace();

            return "找不到版本号";

        }

    }
}
