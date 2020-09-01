package com.p2p.bawei.p2pinvest1801.home.more.reg;

import com.example.framework2.mvp.presenter.BasePresenter;
import com.example.net.activity_bean.LoginBean;
import com.example.net.activity_bean.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegPresenter extends BasePresenter<RegContract.View, RegContract.Model> {


    public RegPresenter(RegContract.Model mModel, RegContract.View mView) {
        super(mModel, mView);
    }
    public void register(){
        mModel.requestReg(mView.getUserName(), mView.getUserPwd(), new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                if (registerBean.getCode().equals(200)){
                    mView.regOk();
                }else {
                    mView.show(registerBean.getResult());
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
