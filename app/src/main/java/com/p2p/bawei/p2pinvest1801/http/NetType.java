package com.p2p.bawei.p2pinvest1801.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetType {
    //判断网络是否连接
    public static boolean isNetContent(Context context){
        if (context != null){
            ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
            if (activeNetworkInfo != null){
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //判断网络连接类型
    public static String NetContentType(Context context){
        if (context != null){
            ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()){
                int type = activeNetworkInfo.getType();
                switch (type){
                    case 0:
                        return "移动数据";
                    case 1:
                        return "WIFI";
                    default:
                        break;
                }
            }
        }
        return "";
    }
}
