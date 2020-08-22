package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.BaseObserver;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView,RegisterContract.RegisterModel> {
    public RegisterPresenter(RegisterContract.RegisterModel mModel, RegisterContract.RegisterView mView) {
        super(mModel, mView);
    }
    public void registerP(){
        mModel.requestregiste(mView.username(), mView.pwd(), new BaseObserver<String>() {
            @Override
            public void onNext(String s) {
                mView.registerView(s);
            }

            @Override
            public void onRequestError(String code, String message) {
                mView.showError(code, message);
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                gDisposable = disposable;
            }
        });
    }
}
