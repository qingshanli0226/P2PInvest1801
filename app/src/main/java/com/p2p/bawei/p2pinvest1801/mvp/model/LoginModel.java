package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.bean.BaseBean;
import com.example.common.bean.LoginBean;
import com.example.framwork.mvp.model.BaseModel;
import com.example.net.BaseObserver;
import com.example.net.NetFunction;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.LoginModel {

    @Override
    public void requestlogin(String username, String pwd, BaseObserver<LoginBean> observer, Consumer<Disposable> consumer) {
        HashMap<String,String> params = new HashMap<>();
        params.put("name", username);
        params.put("password", pwd);
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .login(params)
                .map(new NetFunction<BaseBean<LoginBean>,LoginBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(consumer)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
