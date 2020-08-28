package com.example.framework.base.manager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.example.framework.base.service.FinanceService;
import com.example.net.mode.BannerBean;

import java.util.ArrayList;

public class UserManager {

    //储存activity的集合
    private ArrayList<Activity> activityArrayList = new ArrayList<>();
    private BannerBean bannerBean;
    private String userName;
    private String version;
    private Context context;

    public BannerBean getBannerBean() {
        return bannerBean;
    }

    public void setBannerBean(BannerBean bannerBean) {
        this.bannerBean = bannerBean;
    }

    private FinanceService.FinanceBinder binder;

    public FinanceService.FinanceBinder getBinder() {
        return binder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private UserManager(){}

    private static UserManager userManager;

    public static UserManager getInstance(){
        if(userManager == null){
            userManager = new UserManager();
        }
        return userManager;
    }

    private ServiceConnection serviceConnection =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (FinanceService.FinanceBinder) service;
            binder.getFinanceService().autoLogin(context);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void init(final Context context){
        this.context = context;
        //实现自动登录
        Intent intent = new Intent(context, FinanceService.class);
        //绑定Service
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    //添加activity的方法
    public void addActivity(Activity activity){
        if(!activityArrayList.contains(activity)){
            activityArrayList.add(activity);
        }
    }
    //移除activity的方法
    public void removeActivity(Activity activity){
        if(activityArrayList.contains(activity)){
            activityArrayList.remove(activity);
        }
    }

    public ArrayList<Activity> getActivityArrayList() {
        return activityArrayList;
    }

    //解绑服务的方法
    public void unBindFinanceService(){
        context.unbindService(serviceConnection);
    }
}
