package com.p2p.bawei.p2pinvest1801.invest.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.invest.contract.InvestContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestModel extends BaseModel implements InvestContract.Model {

    @Override
    public void getInvestData(final BaseObserver<InvestBean> baseObserver) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getInvestData()
                .observeOn(AndroidSchedulers.mainThread())

                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

