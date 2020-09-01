package com.p2p.bawei.p2pinvest1801.user.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.user.mvp.contract.RegisterContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterContractModel,RegisterContract.IRegisterContractView> {
    public RegisterPresenter(RegisterContract.IRegisterContractModel mModel, RegisterContract.IRegisterContractView mView) {
        super(mModel, mView);


    }

    public void getRegister(String name,String pwd){

        mModel.getRegister(name, pwd, new Observer<StringBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StringBean stringBean) {
                mView.onRegister(stringBean);
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
