package com.p2p.bawei.p2pinvest1801.welcome.model;

import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.welcome.center.WelContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class WelComeModel extends BaseModel implements WelContract.Model {


    @Override
    public void getAppCode(BaseObserver<WelComeUpAppBean> baseObserver) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getAppData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

}
