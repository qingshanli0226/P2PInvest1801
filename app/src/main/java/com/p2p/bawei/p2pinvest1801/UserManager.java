package com.p2p.bawei.p2pinvest1801;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.p2p.bawei.p2pinvest1801.api.MyApi;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.service.MySservice;

import java.util.LinkedList;
import java.util.List;


//用单例存储当前登录状态
public class UserManager {

    private static UserManager instance;
    private LoginBean loginBean;

    private List<ILoginStatusListener> loginStatusListeners = new LinkedList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String P2PSp="p2pSp";
    private String tokenName="tokenSp";

    private UserManager(){

    }

    public static UserManager getInstance(){
        if(instance==null){
            instance=new UserManager();
        }

        return instance;
    }

    public void init(Context context){//实现自动登录

        sharedPreferences = context.getSharedPreferences(P2PSp,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        Intent intent = new Intent();
        intent.setClass(context, MyApi.class);
        context.bindService(intent, new ServiceConnection() {//绑定service,获取其接口,调用其方法
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                MySservice.KsBinder ksBinder = (MySservice.KsBinder) iBinder;
                if(!TextUtils.isEmpty(getToken())){//如果用户名密码不为空就自动登录
                    ksBinder.getService().autoLogin(getToken());
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },Context.BIND_AUTO_CREATE);
    }

    public void setLoginBean(LoginBean loginBean){
        this.loginBean = loginBean;
        //去通知当前用户成功登陆
        if(loginStatusListeners.size()>0){
            for(ILoginStatusListener listener:loginStatusListeners){
                listener.onLoginSuccess(loginBean);
            }
        }
        editor.putString(tokenName,loginBean.getResult().getToken());//存储token
        editor.commit();//提交
        Log.i("wby", "setLoginBean: "+loginBean.getResult().getToken());
    }


    //对外提供一个接口用来获取token
    public String getToken(){
        return sharedPreferences.getString(tokenName,"");
    }

    //添加一个通知接口,监听登录状态
    public interface ILoginStatusListener{
        void onLoginSuccess(LoginBean loginBean);
        void onLoginOut();
    }

}
