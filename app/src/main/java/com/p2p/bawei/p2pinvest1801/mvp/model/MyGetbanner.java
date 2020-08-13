package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.http.HttpManager;
import com.example.common.mvp.model.BaseModel;
import com.google.gson.JsonObject;
import com.p2p.bawei.p2pinvest1801.bean.EdtivitnEnitiy;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyGetbanner implements MyContract.mModle {
    @Override
    public void getbanner(Observer<MyBannerEntity> observer) {
        HttpManager.getInstance().getRetrofit()
                .create(Api.class)
                .getbanner()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void getedtivity(Observer<EdtivitnEnitiy> observer) {
        HttpManager.getInstance().getRetrofit()
                .create(Api.class)
                .getedtivity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void Destory() {

    }
}
