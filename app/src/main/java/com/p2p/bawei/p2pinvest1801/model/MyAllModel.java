package com.p2p.bawei.p2pinvest1801.model;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.bw.lib_core.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.app.MyApi;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyAllModel extends BaseModel implements MyAllContract.Model {

    @Override
    public void request_all(BaseObserver<MyAllBean> observer) {
        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_all()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
