package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.lib_core.http.Http;
import com.example.lib_core.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.API;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestmentAllFinancialContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestmentAllFinancialModel extends BaseModel implements InvestmentAllFinancialContract.Model {

    @Override
    public void getData(Observer<InvestmentBean> observer) {
        Http.getInstance().creatRetrofit()
                .create(API.class)
                .InvestmentData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
