package com.p2p.bawei.p2pinvest1801.mvp.model;

import android.util.Log;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel extends BaseModel implements HomeContract.IHomeContractModle {
//    @Override
//    public void getHomeBean() {
//        NetRetrofitManager.getInstance()
//                .getRetrofit()
//                .create(Api.class)
//                .homeBeans()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<HomeBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(HomeBean homeBean) {
//                        Log.i("liuxuan", "onNext: "+homeBean.getCode()+"     size"+homeBean.getResult().getImageArr().size());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

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
}
