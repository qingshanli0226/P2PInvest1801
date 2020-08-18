package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.common.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyRegisterContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyRegisterPresenter extends BasePresenter<MyRegisterContract.mModle,MyRegisterContract.mView> {
    public MyRegisterPresenter(MyRegisterContract.mModle mModel, MyRegisterContract.mView mView) {
        super(mModel, mView);
    }
    private void getregister(){
        mModel.getregister(new Observer<MyRegisterEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyRegisterEntity myRegisterEntity) {
                mView.getregister(myRegisterEntity);
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
