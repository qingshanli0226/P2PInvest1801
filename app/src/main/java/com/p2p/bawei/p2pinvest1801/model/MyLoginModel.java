package com.p2p.bawei.p2pinvest1801.model;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.bw.lib_core.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.MyApi;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.contract.MyLoginContract;
import com.p2p.bawei.p2pinvest1801.contract.MyRegisterContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyLoginModel extends BaseModel implements MyLoginContract.Model {


    @Override
    public void request_login(String username, String pwd, BaseObserver<LoginBean> observer) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",username);
        map.put("pwd",pwd);

        HttpRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
