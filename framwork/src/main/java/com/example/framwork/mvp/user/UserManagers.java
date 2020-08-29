package com.example.framwork.mvp.user;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.example.common.bean.LoginBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManagers {
    private static UserManagers userManagers;
    private LoginBean loginBean;
    private Context context;
    private List<Activity> list_activity = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<ILoginStatusChangeListener> loginStatusChangeListeners = new LinkedList<>();
    public static UserManagers getInstance(){
        if(userManagers == null){
            userManagers = new UserManagers();
        }
        return userManagers;
    }
    ServiceConnection serviceConnection;
    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("tokens", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Intent intent = new Intent(context, UserService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                UserService.MyBind myBinder = (UserService.MyBind) service;
                if (!TextUtils.isEmpty(getToken())) {
                    myBinder.getBind().init(getToken());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        this.context = context;
    }

    //该函数，将当前应用程序的登录状态由未登录改成已登录
    public void saveLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;

        if (loginStatusChangeListeners.size()>0) {
            for(ILoginStatusChangeListener listener:loginStatusChangeListeners) {
                listener.onLoginSuccess(loginBean);
            }
        }
        //使用sp存储token
        editor.putString("token", loginBean.getToken());
        editor.commit();
    }
    //用户退出登录时调用，该函数处理缓存,会将用户登录后存储的缓存清空
    public void processLogout() {
        this.loginBean = null;//内存登录状态变为未登录
        editor.putString("token", "");
        editor.commit();

        //去通知各个页面当前用户已退出登录
        if (loginStatusChangeListeners.size()>0) {
            for(ILoginStatusChangeListener listener:loginStatusChangeListeners) {
                listener.onLogoutSuccess();
            }
        }
    }
    //判断当前用户是否登录
    public boolean isUserLogin() {
        return loginBean != null;//如果loginBean不为空则代表已经登录
    }
    //判断当前用户是否登录
    public String getetname() {
        String name = loginBean.getName();
        if(name !=null){
            return name;
        }
        return "";
    }
    private String versions;
    private String url;
    public void setVersion(String version){
        this.versions = version;
    }
    public void setVersionurl(String url){
        this.url = url;
    }
    public String getVersionurl(){
        return url;
    }
    public String getVersion(){
        return versions;
    }
    public void setLoginStatusChangeListener(ILoginStatusChangeListener listener) {
        if (!loginStatusChangeListeners.contains(listener)) {
            loginStatusChangeListeners.add(listener);
        }
    }

    public void removeLoginStatusChangeListener(ILoginStatusChangeListener listener) {
        if (loginStatusChangeListeners.contains(listener)) {
            loginStatusChangeListeners.remove(listener);
        }
    }
    public String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("tokens",Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    //添加一个通知接口，当用户登录或者退出登录，通知监听状态的页面
    public interface ILoginStatusChangeListener{
        void onLoginSuccess(LoginBean loginBean);
        void onLogoutSuccess();
    }

    public void addActivity(Activity activity){
        if(!list_activity.contains(activity)){
            list_activity.add(activity);
        }
    }
    public void removeActivity(Activity activity){
        if (list_activity.contains(activity)) {
            list_activity.remove(activity);
        }
    }

    public void removeService(){
        if(serviceConnection == null){
            return;
        }
        context.unbindService(serviceConnection);
    }
    public List<Activity> getList_activity(){
        return list_activity;
    }
}
