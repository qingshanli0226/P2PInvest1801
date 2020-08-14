package com.p2p.bawei.p2pinvest1801.invest.mvp.model;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;
import com.p2p.bawei.p2pinvest1801.invest.mvp.contract.InvestContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InvestModel extends BaseModel implements InvestContract.IInvestContractModel {

    @Override
    public void getInvestAllBean(Observer<InvestAllBean> observer) {

        NetRetrofitManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .investAllBeans()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }


}
