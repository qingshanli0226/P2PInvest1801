package com.p2p.bawei.p2pinvest1801.user_act.presenter;

import android.util.Log;

import com.example.baselibrary.mvp.presenter.BasePresenter;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.user_act.contract.LoginContract;
import com.p2p.bawei.p2pinvest1801.user_act.contract.RegisterContract;

public class RegisterPresenter extends BasePresenter<RegisterContract.View, RegisterContract.Model> {
    public RegisterPresenter(RegisterContract.View mView, RegisterContract.Model mModel) {
        super(mView, mModel);
    }

    public void getRegisterBeanData() {
        String userName = mView.getUserName();
        Log.e("hq", "getRegisterBeanData: "+userName );
        String userPWD = mView.getUserPWD();
        if (userName.length() == 0) {
            mView.showMessage("用户名不能为空");
            return;
        }
        if (userPWD.length() == 0) {
            mView.showMessage("密码不能为空");
            return;
        }
        mModel.getData(new BaseObserver<RegisterBean>() {
            @Override
            public void success(RegisterBean registerBean) {
                if (registerBean.getCode().equals("200")) {
                    mView.goMain();
                } else {
                    mView.showMessage(registerBean.getMessage());
                }
            }

            @Override
            public void error(String errorMessage) {
                Log.e("hq", "error: "+errorMessage );
            }
        },userName,userPWD);
    }
}
