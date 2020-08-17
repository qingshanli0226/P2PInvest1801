package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.bw.framwork.presenter.BasePresenter;
import com.bw.net.bean.AllLCBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.AllContract;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class AllPresenter extends BasePresenter<AllContract.Model,AllContract.View> {
    public AllPresenter(AllContract.Model mModel, AllContract.View mView) {
        super(mModel, mView);
    }

    public void data(){
        mModel.getData(new MyCallBack<AllLCBean>() {
            @Override
            public void onNext(AllLCBean allLCBean) {
                super.onNext(allLCBean);
                if (allLCBean != null) {
                    mView.setData(allLCBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mView.showLoading();
            }
        },new Action() {
            @Override
            public void run() throws Exception {
                mView.hideLoading();
            }
        });
    }
}
