package com.p2p.bawei.p2pinvest1801.user_act.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.example.net.utils.EncryptUtil;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.RegisterContract;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel implements RegisterContract.Model {
    @Override
    public void getData(BaseObserver<RegisterBean> baseObserver, String name, String pwd) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:9999");

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("name", name);
        treeMap.put("password", pwd);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : treeMap.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            stringBuilder.append(key + "=" + value + "&");
        }
        stringBuilder.append("encrypt=md5");
        String s = EncryptUtil.enrypByMd5(stringBuilder.toString());
        treeMap.put("sign", s);
        TreeMap<String, String> treeMap1 = EncryptUtil.encryptParamsValueByBase64(treeMap);

        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getRegisterData(treeMap1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);
    }

}
