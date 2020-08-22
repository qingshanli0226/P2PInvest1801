package com.example.framework;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    //单例模式：饿汉式
    private ActivityManager() {
    }

    private static ActivityManager activityManager;

    public synchronized static ActivityManager getInstance() {
        if (activityManager == null) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    //提供栈的对象
    private final Stack<Activity> activityStack = new Stack<>();

    //activity的添加
    public void add(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    //插曲：[12,3,44,6,332,65,-56,1]
    //删除指定的activity
    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();//销毁当前的activity
                    activityStack.remove(i);//从栈空间移除
                }
            }
        }
    }

    //删除当前的activity
    public void removeCurrent() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的activity
    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    //返回栈大小
    public int size() {
        return activityStack.size();
    }


}
