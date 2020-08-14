package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.http.HttpManager;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyInvestContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyInvestModel implements MyInvestContract.mModel {
    @Override
    public void getinvest(Observer<MyInestEntivity> observer) {
        HttpManager.getInstance().getRetrofit()
                .create(Api.class)
                .getintivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void Destory() {

    }
}
