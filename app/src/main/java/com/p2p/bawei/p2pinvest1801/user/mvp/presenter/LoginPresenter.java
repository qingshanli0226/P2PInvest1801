package com.p2p.bawei.p2pinvest1801.user.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.contract.LoginContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginContractModel,LoginContract.ILoginContractView> {

    public LoginPresenter(LoginContract.ILoginContractModel mModel, LoginContract.ILoginContractView mView) {
        super(mModel, mView);


    }

    public void register(String name, String password){

        mModel.getLogin(name, password, new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean stringBean) {
                mView.onLogin(stringBean);
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
