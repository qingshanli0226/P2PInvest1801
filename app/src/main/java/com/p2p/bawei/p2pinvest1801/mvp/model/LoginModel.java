package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.lib_core.http.Http;
import com.example.lib_core.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.API;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.util.Md5Util;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.Model {


    @Override
    public void setLogin(String name, String pwd, Observer<LoginBean> observer) {

        TreeMap<String, String> stringStringTreeMap = new TreeMap<>();
        stringStringTreeMap.put("name",name);
        stringStringTreeMap.put("pwd",pwd);
        StringBuffer stringBuffer = new StringBuffer();
        for (String key:stringStringTreeMap.keySet()){
            String a =stringStringTreeMap.get(key);
            stringBuffer.append(name+"="+a+"&");
        }
        //生成md5签名
        String sign = Md5Util.ByMd5(stringBuffer.toString());
        stringStringTreeMap.put("sign",sign);
        //使用Base64,防止明文传输
        TreeMap<String, String> stringStringTreeMap1 = Md5Util.ValueBase64(stringStringTreeMap);

        Http.getInstance().creatRetrofit()
                .create(API.class)
                .Login(stringStringTreeMap1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
