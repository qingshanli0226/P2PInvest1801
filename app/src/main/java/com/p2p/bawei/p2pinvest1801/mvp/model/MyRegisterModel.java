package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.http.HttpManager;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyRegisterContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyRegisterModel implements MyRegisterContract.mModle {
    @Override
    public void getregister(Observer<MyRegisterEntity> observer) {
        HttpManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .getregister()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void Destory() {

    }
}
