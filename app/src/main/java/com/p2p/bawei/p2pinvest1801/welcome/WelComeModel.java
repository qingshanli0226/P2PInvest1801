package com.p2p.bawei.p2pinvest1801.welcome;

import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.mvp.model.BaseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WelComeModel extends BaseModel implements WelContract.Model {


    @Override
    public void DownLoadApp(BaseObserver<String> baseObserver) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getAppData()
                .delay(5000, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }
}
