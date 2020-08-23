package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.http.HttpManager;
import com.p2p.bawei.p2pinvest1801.bean.MyUploadEntity;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyUploadContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyUploadModel implements MyUploadContract.mModle {
    @Override
    public void setupload(Observer<MyUploadEntity> observer) {


//        HttpManager.getInstance().getRetrofit()
//                .create(Api.class)
//                .setupload()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);

    }

    @Override
    public void Destory() {

    }
}
