package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.mylibrary.http.HttpManger;
import com.example.mylibrary.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.MainContact;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel extends BaseModel implements MainContact.Model {
    @Override
    public void requestBanner(Observer<BannerBean> observer) {
        HttpManger.getInstance().setUrl("http://49.233.93.155:8080/");
        HttpManger.getInstance()
                .getmRetrofit()
                .create(Api.class)
                .mainOb()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

}
