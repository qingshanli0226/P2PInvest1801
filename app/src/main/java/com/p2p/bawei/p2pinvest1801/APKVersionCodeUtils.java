package com.p2p.bawei.p2pinvest1801;

import android.content.Context;
import android.content.pm.PackageManager;

public class APKVersionCodeUtils {

    private Context mContext;

    private APKVersionCodeUtils() {
    }

    private static APKVersionCodeUtils apkVersionCodeUtils;

    public static APKVersionCodeUtils getInstance(){

        if (apkVersionCodeUtils==null){
            synchronized (String.class){
                if (apkVersionCodeUtils==null){
                    apkVersionCodeUtils=new APKVersionCodeUtils();
                }
            }
        }
        return apkVersionCodeUtils;
    }

    public void init(Context mContext){
        this.mContext=mContext;
    }

    public int getVersionCode(){
        int versionCode=0;

        try {
            versionCode=mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    public String getVersionName(){
        String verName = "";
        try {
            verName = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
