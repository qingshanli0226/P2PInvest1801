package com.p2p.bawei.p2pinvest1801.presenter;

import com.alibaba.fastjson.JSONObject;
import com.example.net.FinanceManager;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UnLoginBean;
import com.p2p.bawei.p2pinvest1801.contract.RegisterLoginContract;
import com.p2p.bawei.p2pinvest1801.until.EncryptUntil;

import java.util.TreeMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterLoginPresenterImpl extends RegisterLoginContract.RegisterLoginPresenter {
    @Override
    public void onGetRegister(String name, String password) {

        //第一步生成签名，保证数据得完整性
        TreeMap<String,String> params = new TreeMap<>();
        //添加数据
        params.put("name",name);
        params.put("password",password);
        StringBuilder sb = new StringBuilder();
        //拼接数据
        for(String key : params.keySet()){
            String value = params.get(key);
            sb.append(key+"="+value+"&");
        }
        sb.append("encrypt=md5");
        //用md5生成签名
        String sign = EncryptUntil.enrypByMd5(sb.toString());
        params.put("sign",sign);

        //第二步对参数的value进行编码加密，防止明文传输
        TreeMap<String, String> stringStringTreeMap = EncryptUntil.encryptParamsValueByBase64(params);

        FinanceManager.getInstance().getFinanceApi()
                .getRegisterData(stringStringTreeMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if(registerBean.getCode().equals("200")){
                            iHttpView.onRegisterData(registerBean);
                        } else{
                            iHttpView.showError(registerBean.getCode()+"",registerBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetLogin(String name, String password) {
        //生成签名
        TreeMap<String,String> params = new TreeMap<>();
        params.put("name",name);
        params.put("password",password);
        //拼接
        StringBuilder sb = new StringBuilder();
        for(String key : params.keySet()){
            String value = params.get(key);
            sb.append(key+"="+value+"&");
        }
        sb.append("encrypt=md5");
        //进行信息摘要(MD5)
        String sign = EncryptUntil.enrypByMd5(sb.toString());
        //加入TreeMap中
        params.put("sign",sign);
        //生成Base64暗文传输
        TreeMap<String, String> stringStringTreeMap = EncryptUntil.encryptParamsValueByBase64(params);

        //进行网络请求
        FinanceManager.getInstance().getFinanceApi()
                .getLoginData(stringStringTreeMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(loginBean.getCode().equals("200")){
                            iHttpView.onLoginData(loginBean);
                        } else{
                            iHttpView.showError(loginBean.getCode()+"",loginBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onLoginOut() {

        FinanceManager.getInstance().getFinanceApi()
                .getLoginOutData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UnLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UnLoginBean unLoginBean) {
                        if(unLoginBean.equals("200")){
                            iHttpView.onLoginOutData(unLoginBean);
                        } else{
                            iHttpView.showError(unLoginBean.getCode()+"",unLoginBean.getMessage());
                        }
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
