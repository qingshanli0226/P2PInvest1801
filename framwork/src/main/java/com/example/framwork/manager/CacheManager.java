package com.example.framwork.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    private List<Activity> activityList = new ArrayList<>();//存储所有在后台的Activity实例，便于结束进程
    private static CacheManager instance;

    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }


    public void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
