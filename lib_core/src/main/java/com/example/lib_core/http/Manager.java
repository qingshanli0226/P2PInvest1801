package com.example.lib_core.http;

/*

    管理类

 */

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private List<Activity> activity_list = new ArrayList<>();//用来存储后台所有的activity,当出现未知异常时,结束进程
    private static Manager instance;
    public static Manager getInstance(){
        if (instance == null){
            instance = new Manager();
        }
        return instance;
    }

    public void addActivity(Activity activity){
        //判断集合中是否不包含当前添加的activity
        if (!activity_list.contains(activity)){
            activity_list.add(activity);
        }
    }

    public void removeActivity(Activity activity){
        if (activity_list.contains(activity)){
            activity_list.remove(activity);
        }
    }

    public List<Activity> getActivity_list(){
        return activity_list;
    }


}
