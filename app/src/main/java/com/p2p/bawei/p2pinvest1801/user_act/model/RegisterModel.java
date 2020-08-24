package com.p2p.bawei.p2pinvest1801.user_act.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.google.gson.Gson;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.user_act.bean.BodyBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user_act.contract.RegisterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterModel extends BaseModel implements RegisterContract.Model {
    @Override
    public void getData(BaseObserver<RegisterBean> baseObserver, String name, String pwd) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:8080");
        String s = new Gson().toJson(new BodyBean(name, pwd));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getRegisterData(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);
    }

}
