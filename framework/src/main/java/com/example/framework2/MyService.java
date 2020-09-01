package com.example.framework2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.example.framework2.manager.CacheManager;
import com.example.net.activity_bean.LoginBean;
import com.example.net.api_srever.ApiServer;
import com.example.net.http.HttpManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
    public void autoLogin(String token){
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(ApiServer.class)
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
                            CacheManager.getInstance().setLoginBean(loginBean);
                            Toast.makeText(MyService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
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
