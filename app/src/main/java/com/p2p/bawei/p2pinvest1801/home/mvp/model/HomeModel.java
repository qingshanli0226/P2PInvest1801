package com.p2p.bawei.p2pinvest1801.home.mvp.model;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.home.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel implements HomeContract.IHomeContractModle {

    @Override
    public void getHomeBean(Observer<HomeBean> observer) {
        NetRetrofitManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .homeBeans()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getUpDateBean(Observer<UpDateBean> observer) {
        NetRetrofitManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .upDateBeans()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
