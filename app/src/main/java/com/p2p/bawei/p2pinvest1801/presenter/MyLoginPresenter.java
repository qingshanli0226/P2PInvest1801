package com.p2p.bawei.p2pinvest1801.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.contract.MyLoginContract;
import com.p2p.bawei.p2pinvest1801.contract.MyRegisterContract;

public class MyLoginPresenter extends BasePresenter<MyLoginContract.Model,MyLoginContract.View> {
    public MyLoginPresenter(MyLoginContract.Model mModel, MyLoginContract.View mView) {
        super(mModel, mView);
    }

    public void get_login(){
        mModel.request_login(mView.getUsername(), mView.getPwd(), new BaseObserver<LoginBean>() {
            @Override
            public void success(LoginBean mybean) {
                mView.goMain(mybean);
            }
        });
    }
}
