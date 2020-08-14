package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.mylibrary.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.AllBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.AllContact;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AllPre extends BasePresenter<AllContact.Model,AllContact.View> {

    public AllPre(AllContact.Model mModel, AllContact.View mView) {
        super(mModel, mView);
    }
    public void allList(){
        mModel.requestAll(new Observer<AllBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllBean allBean) {
                    mView.initAll(allBean);
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
