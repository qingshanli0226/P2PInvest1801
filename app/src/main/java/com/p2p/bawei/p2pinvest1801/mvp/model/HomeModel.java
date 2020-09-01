package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.lib_core.http.Http;
import com.example.lib_core.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.API;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel implements HomeContract.Model {
    @Override
    public void getData(Observer<HomeBean> observer) {
        Http.getInstance().creatRetrofit()
                .create(API.class)
                .HomeBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void getUpdateMsg(Observer<UpDateBean> observer) {

        Http.getInstance().creatRetrofit()
                .create(API.class)
                .upDate()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
