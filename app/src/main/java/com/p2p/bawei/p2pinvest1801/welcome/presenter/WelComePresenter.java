package com.p2p.bawei.p2pinvest1801.welcome.presenter;

import android.util.Log;

import com.example.baselibrary.mvp.presenter.BasePresenter;
import com.example.common.AppCode;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.welcome.center.WelContract;


public class WelComePresenter extends BasePresenter<WelContract.View, WelContract.Model> {


    public WelComePresenter(WelContract.View mView, WelContract.Model mModel) {
        super(mView, mModel);
    }

    public void getData() {
        mModel.getAppCode(new BaseObserver<WelComeUpAppBean>() {

            @Override
            public void success(WelComeUpAppBean welComeUpAppBean) {

                String code = welComeUpAppBean.getResult().getVersion();
                String version = AppCode.getInstance().getVersion();

                if (!code.equals(version)) {
                    mView.upAppCode(welComeUpAppBean.getResult());
                } else {
                    mView.upAppCode(null);
                }
            }

            @Override
            public void error(String errorMessage) {
                Log.e("hq", "error: " + errorMessage);
            }
        });
    }

}
