package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import android.util.Log;

import com.example.common.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyGetBannerPresenter extends BasePresenter<MyContract.mModle,MyContract.mView> {
    public MyGetBannerPresenter(MyContract.mModle mModel, MyContract.mView mView) {
        super(mModel, mView);
    }
    public void getbanner(){
        mModel.getbanner(new Observer<MyBannerEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyBannerEntity myBannerEntity) {
                mView.getbanner(myBannerEntity);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("bannermessage", "onError: 请求失败"+e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
