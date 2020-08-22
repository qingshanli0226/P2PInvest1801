package com.p2p.bawei.p2pinvest1801.model;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.http.HttpRetrofitManager;
import com.bw.lib_core.http.PwdRetrofitManager;
import com.bw.lib_core.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.app.MyApi;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.contract.MyRegisterContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyRegisterModel extends BaseModel implements MyRegisterContract.Model {

    @Override
    public void request_register(String username, String pwd, BaseObserver<RegisterBean> observer) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name",username);
        map.put("pwd",pwd);

        PwdRetrofitManager.getInstance().getRetrofit()
                .create(MyApi.class)
                .get_register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
