package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.bw.framwork.model.BaseModel;
import com.bw.net.RetrofitManager;
import com.bw.net.api.Api;
import com.bw.net.bean.LoginBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public void login(String name, String pwd, MyCallBack<LoginBean> callBack) {
        RetrofitManager.getInstance().retrofit
                .create(Api.class)
                .login(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }
}
