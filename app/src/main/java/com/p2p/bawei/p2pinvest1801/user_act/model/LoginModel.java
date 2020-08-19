package com.p2p.bawei.p2pinvest1801.user_act.model;

import android.util.Log;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.http.MD5Utils;
import com.example.net.observer.BaseObserver;
import com.example.net.utils.MD5Util;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public void getData(BaseObserver<LoginBean> baseObserver, String name, String pwd) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:9999");

        TreeMap<String, String> hashMap = new TreeMap<>();
        StringBuilder str = new StringBuilder();
        for (String s : hashMap.keySet()) {
            String s1 = hashMap.get(s);
            str.append(s).append("=").append(s1).append("&");
        }
        str.append("encrypt=MD5");
        String s = MD5Utils.md5(str.toString());
        hashMap.put("sign",s);
        Log.e("hq", "getData: "+s );
        TreeMap<String, String> treeMap = MD5Utils.ValuesBase64(hashMap);

        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getLoginData(treeMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);
    }
}
