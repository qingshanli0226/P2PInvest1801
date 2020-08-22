package com.p2p.bawei.p2pinvest1801.mvp.presenter;


import com.example.lib_core.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> {
    public HomePresenter(HomeContract.Model mModel, HomeContract.View mView) {
        super(mModel, mView);
    }

    public void getData(){
        mModel.getData(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                if (mView!=null){
                    mView.initHomeData(homeBean);
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
