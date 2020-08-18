package com.example.user.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.example.API;
import com.example.bean.LoginBean;
import com.example.user.contract.LoginContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public void getData(BaseObserver<LoginBean> baseObserver, String name, String pwd) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(API.class)
                .getLoginData(name, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);

    }
}
