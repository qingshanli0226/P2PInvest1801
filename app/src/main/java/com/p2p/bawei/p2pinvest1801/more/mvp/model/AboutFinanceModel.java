package com.p2p.bawei.p2pinvest1801.more.mvp.model;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;
import com.p2p.bawei.p2pinvest1801.more.mvp.contract.AboutFinanceContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class AboutFinanceModel extends BaseModel implements AboutFinanceContract.IAboutFinanceModel {
    @Override
    public void getAboutFinance(Observer<AboutFinanceBean> observer) {
        NetRetrofitManager.getInstance().getRetrofit()
                .create(Api.class)
                .getAlbumFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
