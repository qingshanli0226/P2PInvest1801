package com.example.framework2.manager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;

import com.example.common.NetCommon;
import com.example.framework2.MyService;
import com.example.net.activity_bean.IndexBean;
import com.example.net.activity_bean.InvestBean;
import com.example.net.activity_bean.LoginBean;
import com.example.net.activity_bean.UpdateBean;


import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    private CacheManager() {
    }

    private LoginBean loginBean;

    private SharedPreferences sharedPreferences;
    private boolean isGeted=false;
    private List<Activity> activityList=new ArrayList<>();
    private static CacheManager instance;
    public static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }
    private IndexBean indexBean;
    private UpdateBean updateBean;
    private InvestBean investBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(NetCommon.SP, Context.MODE_PRIVATE);
        Intent intent = new Intent(context, MyService.class);
        ServiceConnection serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder=(MyService.MyBinder)service;
                if (!TextUtils.isEmpty(getToken())){
                    myBinder.getService().autoLogin(getToken());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    private String getToken() {
        return sharedPreferences.getString(NetCommon.SP,null);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public boolean isGeted() {
        return isGeted;
    }

    public void setGeted(boolean geted) {
        isGeted = geted;
    }

    public InvestBean getInvestBean() {
        return investBean;
    }

    public void setInvestBean(InvestBean investBean) {
        this.investBean = investBean;
    }

    public IndexBean getIndexBean() {
        return indexBean;
    }

    public void setIndexBean(IndexBean indexBean) {
        this.indexBean = indexBean;
    }

    public UpdateBean getUpdateBean() {
        return updateBean;
    }

    public void setUpdateBean(UpdateBean updateBean) {
        this.updateBean = updateBean;
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
    public void notifyLoginState(LoginBean loginBean,LoginINCallback loginINCallback){
        if (loginINCallback!=null){
            loginINCallback.OnLoginNotifyCallback(loginBean);
        }
    }
    public interface LoginINCallback{
        void OnLoginNotifyCallback(LoginBean loginBean);
    }
}
