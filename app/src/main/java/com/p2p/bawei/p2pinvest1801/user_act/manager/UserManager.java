package com.p2p.bawei.p2pinvest1801.user_act.manager;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;

public class UserManager {
    private static UserManager userManager;
    Context context;
    private String token;
    private SharedPreferences userSp;
    private SharedPreferences.Editor edit;
    LoginBean loginBean;

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
        edit.putString("token", loginBean.getResult().getToken());
        edit.apply();
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public String getToken() {
        return userSp.getString("token", "");
    }


    @SuppressLint("CommitPrefEdits")
    public void init(Context context) {
        this.context = context;
        userSp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        edit = userSp.edit();

    }


    //添加一个通知接口，当用户登录或者退出登录，通知监听状态的页面
    public interface ILoginStatusChangeListener {
        void onLoginSuccess(LoginBean loginBean);

        void onLogoutSuccess();//退出登录成功
    }
}
