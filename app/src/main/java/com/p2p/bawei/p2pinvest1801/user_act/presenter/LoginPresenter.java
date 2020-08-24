package com.p2p.bawei.p2pinvest1801.user_act.presenter;

import android.util.Log;

import com.example.baselibrary.mvp.presenter.BasePresenter;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> {
    public LoginPresenter(LoginContract.View mView, LoginContract.Model mModel) {
        super(mView, mModel);
    }

    public void getLoginData() {
        String userName = mView.getUserName();
        String userPWD = mView.getUserPWD();
        if (userName.length() == 0) {
            mView.showMessage("用户名不能为空");
            return;
        }
        if (userPWD.length() == 0) {
            mView.showMessage("密码不能为空");
            return;
        }
        mModel.getData(new BaseObserver<LoginBean>() {
            @Override
            public void success(LoginBean loginBean) {
                if (loginBean.getCode().equals("200")) {
                    mView.goMain(loginBean.getResult());
                } else {
                    mView.showMessage(loginBean.getMessage());
                }
            }

            @Override
            public void error(String errorMessage) {
                Log.e("hq", "error: " + errorMessage);
            }
        }, userName, userPWD);
    }
}
