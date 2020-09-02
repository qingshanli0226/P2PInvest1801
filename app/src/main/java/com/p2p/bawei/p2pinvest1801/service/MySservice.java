package com.p2p.bawei.p2pinvest1801.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.bw.lib_core.http.HttpRetrofitManager;
import com.p2p.bawei.p2pinvest1801.UserManager;
import com.p2p.bawei.p2pinvest1801.api.MyApi;
import com.p2p.bawei.p2pinvest1801.bean.AutoLoginBean;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//完成自动登录
public class MySservice extends Service {

    public class KsBinder extends Binder{
        public MySservice getService(){
            return MySservice.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new KsBinder();
    }

    //自动登录
    public void autoLogin(String tokenValue) {

        HashMap<String, String> map = new HashMap<>();
        map.put("token", tokenValue);
        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_autoLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {

                        if(loginBean.getCode().equals("200")){//存起来
                            UserManager.getInstance().setLoginBean(loginBean);
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
