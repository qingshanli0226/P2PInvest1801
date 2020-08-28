package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.bw.framwork.presenter.BasePresenter;
import com.bw.net.bean.LoginBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.LoginContract;

public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> {

    public LoginPresenter(LoginContract.Model mModel, LoginContract.View mView) {
        super(mModel, mView);
    }

    public void login(){
        mModel.login(mView.getname(), mView.getpwd(), new MyCallBack<LoginBean>() {
            @Override
            public void onNext(LoginBean loginBean) {
                if (loginBean.getCode().equals("200")){
                    mView.loginOk(loginBean);
                }else {
                    mView.showError(Integer.parseInt(loginBean.getCode()),loginBean.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
