package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.bw.framwork.model.BaseModel;
import com.bw.net.RetrofitManager;
import com.bw.net.api.Api;
import com.bw.net.bean.AllLCBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.AllContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllModel extends BaseModel implements AllContract.Model {

    @Override
    public void getData(MyCallBack<AllLCBean> callBack,  Consumer<Disposable> consumer,Action action) {
        RetrofitManager.getInstance().retrofit
                .create(Api.class)
                .getAllLCBean()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(consumer)
                .doFinally(action)

                .subscribe(callBack);
    }
}
