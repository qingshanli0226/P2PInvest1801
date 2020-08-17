package com.p2p.bawei.p2pinvest1801.user.presenter;

import android.util.Log;

import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.contract.LoginContract;

import java.util.HashMap;

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

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);

        RetrofitManager.getInvestApiService()
                .onUserLogin(params)
                .subscribeOn(Schedulers.io())
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
