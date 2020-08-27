package com.p2p.bawei.p2pinvest1801.user_act.model;

import com.example.baselibrary.mvp.model.BaseModel;
import com.example.net.http.HttpManager;
import com.example.net.observer.BaseObserver;
import com.example.net.utils.EncryptUtil;
import com.p2p.bawei.p2pinvest1801.Api;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;

import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public void getData(BaseObserver<LoginBean> baseObserver, String name, String pwd) {
        HttpManager.getHttpManager().setPath("http://49.233.93.155:9999");
        //第一步生成签名,保证数据的完整性
        //先对参数进行排序，按照key的升序拼接成一个字符串(例如"name=666666&password=666"),利用TreeMap数据结构，TreeMap在遍历数据时，默认是按照key的升序遍历的。我们也可以配置TreeMap让它按照key
        //的降序遍历
        TreeMap<String, String> params = new TreeMap<>();
        params.put("name", "1");
        params.put("password", "1");
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            sb.append(key + "=" + value + "&");
        }
        sb.append("encrypt=md5");
        //用md5生成签名
        String sign = EncryptUtil.enrypByMd5(sb.toString());
        params.put("sign", sign);
        //第二步对参数的value进行编码加密，防止明文传输
        TreeMap<String, String> encryptedMap = EncryptUtil.encryptParamsValueByBase64(params);


        HttpManager.getHttpManager()
                .getRetrofit()
                .create(Api.class)
                .getLoginData(encryptedMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);
    }
}
