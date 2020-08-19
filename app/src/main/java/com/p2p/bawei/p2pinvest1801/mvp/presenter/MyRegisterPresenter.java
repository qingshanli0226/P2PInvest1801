package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import android.util.Log;

import com.example.common.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyRegisterContract;
import com.p2p.bawei.p2pinvest1801.utils.EncryptUtil;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyRegisterPresenter extends BasePresenter<MyRegisterContract.mModle,MyRegisterContract.mView> {
    public MyRegisterPresenter(MyRegisterContract.mModle mModel, MyRegisterContract.mView mView) {
        super(mModel, mView);
    }
    public void getregister(){

        String name = mView.getusername();
        String password = mView.getpaw();
//        //第一步生成签名,保证数据的完整性
//        //先对参数进行排序，按照key的升序拼接成一个字符串(例如"name=666666&password=666"),利用TreeMap数据结构，TreeMap在遍历数据时，默认是按照key的升序遍历的。我们也可以配置TreeMap让它按照key
//        //的降序遍历
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("name", name);
//        params.put("password", password);
//        StringBuilder sb = new StringBuilder();
//        for(String key:params.keySet()) {
//            String value = params.get(key);
//            sb.append(key+"="+value+"&");
//        }
//        sb.append("encrypt=md5");
//        Log.d("cx", sb.toString());
//        //用md5生成签名
//        String sign = EncryptUtil.enrypByMd5(sb.toString());
//        params.put("sign", sign);
//
//        //第二步对参数的value进行编码加密，防止明文传输
//        TreeMap<String,String> encryptedMap = EncryptUtil.encryptParamsValueByBase64(params);


        mModel.getregister(name,password,new Observer<MyRegisterEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyRegisterEntity myRegisterEntity) {
                mView.getregister(myRegisterEntity);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
