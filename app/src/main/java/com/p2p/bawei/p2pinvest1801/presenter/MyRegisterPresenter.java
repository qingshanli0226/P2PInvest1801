package com.p2p.bawei.p2pinvest1801.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.contract.MyRegisterContract;

public class MyRegisterPresenter extends BasePresenter<MyRegisterContract.Model,MyRegisterContract.View> {
    public MyRegisterPresenter(MyRegisterContract.Model mModel, MyRegisterContract.View mView) {
        super(mModel, mView);
    }

    public void get_register(){
        mModel.request_register(mView.getUsername(), mView.getPwd(), new BaseObserver<RegisterBean>() {
            @Override
            public void success(RegisterBean mybean) {
                mView.goMain(mybean);
            }
        });
    }
}
