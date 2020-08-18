package com.p2p.bawei.p2pinvest1801.frist.presenter;

import android.util.Log;

import com.example.baselibrary.mvp.presenter.BasePresenter;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.frist.contract.FirstContract;

public class FirstPresenter extends BasePresenter<FirstContract.View, FirstContract.Model> {

    public FirstPresenter(FirstContract.View mView, FirstContract.Model mModel) {
        super(mView, mModel);
    }

    public void getFirstData() {
        mModel.getFirstData(new BaseObserver<FirstBean>() {
            @Override
            public void success(FirstBean firstBean) {
                mView.upData(firstBean);
            }

            @Override
            public void error(String errorMessage) {
                Log.e("hq", "error: "+errorMessage );
            }
        });
    }
}
