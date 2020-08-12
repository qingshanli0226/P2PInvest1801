package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.bw.framwork.model.BaseModel;
import com.bw.net.RetrofitManager;
import com.bw.net.api.Api;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HdContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HdModel extends BaseModel implements HdContract.Model {
    @Override
    public void getHomeData(MyCallBack<HomeBean> callBack) {

        RetrofitManager.getInstance()
                .retrofit
                .create(Api.class)
                .getHomeBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    @Override
    public void getUpdateBean(MyCallBack<UpdataBean> callBack) {
        RetrofitManager.getInstance()
                .retrofit
                .create(Api.class)
                .getUpdataBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }
}
