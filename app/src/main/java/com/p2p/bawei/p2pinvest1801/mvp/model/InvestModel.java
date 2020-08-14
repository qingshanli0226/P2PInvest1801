package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestModel extends BaseModel implements InvestContract.InvestModel {
    @Override
    public void requestInvest(BaseObserver<InvestBean> observer) {
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(P2PApi.class)
                .investlist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
