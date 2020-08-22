package com.p2p.bawei.p2pinvest1801.mvp.model;

import android.util.Base64;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.BaseObserver;
import com.example.net.NetFunction;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.net.bean.BaseBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.util.EncryptUtil;

import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel implements RegisterContract.RegisterModel {
    @Override
    public void requestregiste(String username, String pwd, BaseObserver<String> observer, Consumer<Disposable> consumer) {
        TreeMap<String,String> params = new TreeMap<>();
        params.put("name", username);
        params.put("password", pwd);
        StringBuilder sb = new StringBuilder();
        for (String key:params.keySet()) {
            String value = params.get(key);
            sb.append(key+"="+value+"&");
        }
        sb.append("encrypt=md5");
        String sign = EncryptUtil.enrypByMd5(sb.toString());

        TreeMap<String,String> encryptedMap = EncryptUtil.encryptParamsValueByBase64(params);
        params.put("sign", sign);
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .register(encryptedMap)
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(consumer)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
