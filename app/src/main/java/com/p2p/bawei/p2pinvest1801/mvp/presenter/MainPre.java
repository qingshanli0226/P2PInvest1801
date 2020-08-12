package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.mylibrary.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.MainContact;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPre extends BasePresenter<MainContact.Model,MainContact.View> {


    public MainPre(MainContact.Model mModel, MainContact.View mView) {
        super(mModel, mView);
    }

    public  void list(){
        mModel.requestBanner(new Observer<BannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BannerBean bannerBean) {
                mView.initBanner(bannerBean);
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
