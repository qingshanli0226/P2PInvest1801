package com.p2p.bawei.p2pinvest1801.welcome;

import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.BasePresenter;

public class WelComePresenter extends BasePresenter<WelContract.View, WelContract.Model> {


    public WelComePresenter(WelContract.View mView, WelContract.Model mModel) {
        super(mView, mModel);
    }

    public void getData() {
        mModel.DownLoadApp(new BaseObserver<String>() {
            @Override
            public void success(String s) {
                mView.upAppCode(s);
            }

            @Override
            public void error(String errorCode, String errorMessage) {

            }
        });
    }
}
