package com.p2p.bawei.p2pinvest1801.home.more.log;

import com.example.common.NetCommon;
import com.example.framework2.manager.CacheManager;
import com.example.framework2.mvp.presenter.BasePresenter;
import com.example.net.activity_bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LogPresenter extends BasePresenter<LogContract.View,LogContract.Model> {


    public LogPresenter(LogContract.Model mModel, LogContract.View mView) {
        super(mModel, mView);
    }
    public void login(){
        mModel.requestLog(mView.getUserName(), mView.getUserPwd(), new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                if (loginBean.getCode().equals("200")){
                    CacheManager.getInstance().setLoginBean(loginBean);
                    CacheManager.getInstance().getSharedPreferences().edit().putString(NetCommon.SP,loginBean.getResult().getToken()).commit();
                    mView.logOk(loginBean);
                }else {
                    mView.showMessage(loginBean.getMessage());
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
