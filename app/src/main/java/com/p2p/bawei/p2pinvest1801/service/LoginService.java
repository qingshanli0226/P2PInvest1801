package com.p2p.bawei.p2pinvest1801.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.bw.net.RetrofitManager;
import com.bw.net.SpManager;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.bw.net.api.Api;
import com.bw.net.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginService extends Service {

    public class LoginBinder extends Binder{
        public LoginService getService(){
            return LoginService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LoginBinder();
    }

    public void autologin(String token){
        RetrofitManager.getInstance().retrofit
                .create(Api.class)
                .autoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode().equals("200")){
                            UserManager.getInstance().saveUserBean(loginBean);
                            SpManager.getInstance().addContents("token",loginBean.getResult().getToken());
                            Toast.makeText(LoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
