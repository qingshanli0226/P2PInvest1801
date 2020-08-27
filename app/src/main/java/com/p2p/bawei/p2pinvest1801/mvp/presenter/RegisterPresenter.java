package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import android.util.Log;

import com.example.lib_core.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.RegisterContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterPresenter extends BasePresenter<RegisterContract.Model,RegisterContract.View> {
    public RegisterPresenter(RegisterContract.Model mModel, RegisterContract.View mView) {
        super(mModel, mView);
    }

    public void register(String phone,String name,String pwd,String rePwd){

        mModel.setRegister(phone, name, pwd, rePwd, new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                Log.e("注册成功", "onNext: " );
                mView.success();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("注册失败", "onError: " );
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
