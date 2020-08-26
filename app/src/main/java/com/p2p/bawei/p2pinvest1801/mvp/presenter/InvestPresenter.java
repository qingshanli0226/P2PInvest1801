package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.BaseObserver;
import com.example.common.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestContract;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class InvestPresenter extends BasePresenter<InvestContract.InvestView,InvestContract.InvestModel> {
    public InvestPresenter(InvestContract.InvestModel mModel, InvestContract.InvestView mView) {
        super(mModel, mView);
    }
    public void investList(){
        mModel.requestInvest(new BaseObserver<InvestBean>() {
            @Override
            public void onNext(InvestBean investBean) {
                mView.invest(investBean);
            }

            @Override
            public void onRequestError(String code, String message) {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                gDisposable = disposable;
                mView.showLoading();

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mView.hideLoading();
            }
        });
    }

}
