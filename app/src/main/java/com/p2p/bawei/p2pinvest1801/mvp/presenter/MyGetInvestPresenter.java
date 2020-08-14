package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.common.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyInvestContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyGetInvestPresenter extends BasePresenter<MyInvestContract.mModel,MyInvestContract.mView> {
    public MyGetInvestPresenter(MyInvestContract.mModel mModel, MyInvestContract.mView mView) {
        super(mModel, mView);
    }
    public void getInvest(){
        mModel.getinvest(new Observer<MyInestEntivity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyInestEntivity myInestEntivity) {
                mView.getinvest(myInestEntivity);
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
