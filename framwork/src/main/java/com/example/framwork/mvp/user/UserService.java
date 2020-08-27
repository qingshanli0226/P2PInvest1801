package com.example.framwork.mvp.user;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.common.bean.BaseBean;
import com.example.common.bean.LoginBean;
import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserService extends Service {
    public class MyBind extends Binder{
        public UserService getBind(){
            return UserService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public void init(String token){
        HashMap<String,String> params = new HashMap<>();
        params.put("token", token);
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .autologin(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean<LoginBean>>() {
                    @Override
                    public void onNext(BaseBean<LoginBean> loginBeanBaseBean) {
                        if(loginBeanBaseBean.getCode().equals("200")){
                            UserManagers.getInstance().saveLoginBean(loginBeanBaseBean.getResult());
                            Toast.makeText(UserService.this, "service 弹吐司:自动登录成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(UserService.this, ""+loginBeanBaseBean.getCode()+":"+loginBeanBaseBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onRequestError(String code, String errorMessgae) {
                        Toast.makeText(UserService.this, ""+code+":"+errorMessgae, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
