package com.p2p.bawei.p2pinvest1801.user.presenter;

import android.util.Log;

import com.example.net.NetFunction;
import com.example.net.RetrofitManager;
import com.example.net.bean.BaseBean;
import com.p2p.bawei.p2pinvest1801.user.contract.RegisterContract;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenterImpl extends RegisterContract.RegisterPresenter {
    @Override
    public void getRegister(String name, String password,String phone) {

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        params.put("phone", phone);

        RetrofitManager.getInvestApiService()
                .onUserRegister(params)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>, String>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.onRegister(s);
                        Log.d("---", "onNext: " + s);
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
