package com.p2p.bawei.p2pinvest1801.home.more.reg;

import com.example.framework2.mvp.model.BaseModel;
import com.example.net.activity_bean.LoginBean;
import com.example.net.activity_bean.RegisterBean;
import com.example.net.api_srever.ApiServer;
import com.example.net.http.HttpManager;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegModel extends BaseModel implements RegContract.Model{
    @Override
    public void requestReg(String name, String pwd, Observer<RegisterBean> observer) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",pwd);
        HttpManager.getHttpManager()
                .getRetrofit()
                .create(ApiServer.class)
                .register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
