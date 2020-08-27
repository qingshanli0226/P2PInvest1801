package com.p2p.bawei.p2pinvest1801.mvp.model;

import android.util.Log;

import com.example.lib_core.http.Http;
import com.example.lib_core.mvp.model.BaseModel;
import com.p2p.bawei.p2pinvest1801.api.API;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;
import com.p2p.bawei.p2pinvest1801.util.Md5Util;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel implements RegisterContract.Model {


    @Override
    public void setRegister( String name, String pwd, String phone, String rePwd,Observer<RegisterBean> observer) {
        TreeMap<String,String> objectObjectHashMap = new TreeMap<>();
        objectObjectHashMap.put("phone",phone);
        objectObjectHashMap.put("name",name);
        objectObjectHashMap.put("pwd",pwd);
        objectObjectHashMap.put("rePwd",rePwd);
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : objectObjectHashMap.keySet()){
            String a = objectObjectHashMap.get(key);
            stringBuffer.append(key+"="+a+"&");
        }
        stringBuffer.append("encrypt=md5");
        Log.e("喵喵", "setRegister: "+stringBuffer.toString() );
        //使用MD5生成签名
        String s = Md5Util.ByMd5(stringBuffer.toString());
        objectObjectHashMap.put("sign",s);
        //对参数的value进行编码加密，防止明文传输
        TreeMap<String, String> stringStringTreeMap = Md5Util.ValueBase64(objectObjectHashMap);

        Http.getInstance().creatRetrofit()
                .create(API.class)
                .Register(stringStringTreeMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
