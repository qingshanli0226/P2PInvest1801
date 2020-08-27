package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.lib_core.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> {
    public LoginPresenter(LoginContract.Model mModel, LoginContract.View mView) {
        super(mModel, mView);
    }

    public void login(String name,String pwd){
        mModel.setLogin(name, pwd, new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                mView.success();
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
