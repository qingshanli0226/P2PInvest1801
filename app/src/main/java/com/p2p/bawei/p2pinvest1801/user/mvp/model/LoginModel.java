package com.p2p.bawei.p2pinvest1801.user.mvp.model;

import android.util.Log;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.utils.EncryptUtil;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.contract.LoginContract;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.ILoginContractModel {
    @Override
    public void getLogin(String name, String password, Observer<LoginBean> observer) {




        //第一步生成签名,保证数据的完整性
        //先对参数进行排序，按照key的升序拼接成一个字符串(例如"name=666666&password=666"),利用TreeMap数据结构，TreeMap在遍历数据时，默认是按照key的升序遍历的。我们也可以配置TreeMap让它按照key
        //的降序遍历
        TreeMap<String, String> params = new TreeMap<>();
        params.put("name", name);
        params.put("password", password);
        StringBuilder sb = new StringBuilder();
        for(String key:params.keySet()) {
            String value = params.get(key);
            sb.append(key+"="+value+"&");
        }
        sb.append("encrypt=md5");
        Log.d("LQS", sb.toString());
        //用md5生成签名
        String sign = EncryptUtil.enrypByMd5(sb.toString());
        params.put("sign", sign);

        //第二步对参数的value进行编码加密，防止明文传输
        TreeMap<String, String> encryptedMap = EncryptUtil.encryptParamsValueByBase64(params);


        NetRetrofitManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .loginBeans(encryptedMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
