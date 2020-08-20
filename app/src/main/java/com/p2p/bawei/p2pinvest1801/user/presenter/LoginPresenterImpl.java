package com.p2p.bawei.p2pinvest1801.user.presenter;

import android.util.Log;

import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.utils.EncryptUtil;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends LoginContract.LoginPresenter {
    @Override
    public void getLogin(String name, String password) {
        Log.d("---", "getLogin: ");

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
        TreeMap<String,String> encryptedMap = EncryptUtil.encryptParamsValueByBase64(params);

        RetrofitManager.getInvestApiService()
                .onUserLogin(encryptedMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.onLogin(loginBean);
                        Log.d("---", "onNext: " + loginBean.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("---", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
