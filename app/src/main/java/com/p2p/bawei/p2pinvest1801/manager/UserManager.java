package com.p2p.bawei.p2pinvest1801.manager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.bw.net.SpManager;
import com.bw.net.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.service.LoginService;

import java.util.ArrayList;

public class UserManager {

    private UserManager() {
    }

    private static UserManager userManager ;
    public static UserManager getInstance(){

        if (userManager==null){
            synchronized (String.class){
                if (userManager==null){
                    userManager=new UserManager();
                }
            }
        }
        return userManager;
    }

    private LoginBean loginBean=null;

    public void saveUserBean(LoginBean loginBean){
        this.loginBean=loginBean;
    }

    public boolean isLogin(){
        return loginBean!=null;
    }

    public LoginBean getLoginBean(){
        return this.loginBean;
    }

    public void init(Context context){
        Intent intent = new Intent(context, LoginService.class);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LoginService.LoginBinder loginBinder=(LoginService.LoginBinder)service;
                LoginService service1 = loginBinder.getService();
                if (SpManager.getInstance().isHaveToken()){
                    service1.autologin(SpManager.getInstance().getContents("token"));
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

    }


    private ArrayList<Activity> activities=new ArrayList<>();
    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }
}
