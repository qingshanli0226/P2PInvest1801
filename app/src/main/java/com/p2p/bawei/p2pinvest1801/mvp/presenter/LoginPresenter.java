package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.common.bean.LoginBean;
import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.BaseObserver;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter<LoginContract.LoginView,LoginContract.LoginModel> {

    public LoginPresenter(LoginContract.LoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);

    }

    public void loginP(){
        mModel.requestlogin(mView.username(), mView.pwd(), new BaseObserver<LoginBean>() {
            @Override
            public void onNext(LoginBean loginBean) {
                mView.loginView(loginBean);

            }

            @Override
            public void onRequestError(String code, String message) {
                mView.showError(""+code, ""+message);
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                gDisposable = disposable;
            }
        });
    }
}
