package com.p2p.bawei.p2pinvest1801.frist.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.frist.contract.FirstContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FirstModel extends BaseModel implements FirstContract.Model {
    @Override
    public void getFirstData(BaseObserver<FirstBean> beanBaseObserver) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getFirstData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(beanBaseObserver);
    }
}
