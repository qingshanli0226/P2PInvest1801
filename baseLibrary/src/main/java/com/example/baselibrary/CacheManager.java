package com.example.baselibrary;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;

public class CacheManager {
    private List<Activity> activityList = new ArrayList<>();//存储所有在后台的Activity实例，便于结束进程
    private static CacheManager cacheManager;

    public static CacheManager getCacheManager() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public void addAct(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public void removeAct(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
