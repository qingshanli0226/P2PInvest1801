package com.p2p.bawei.p2pinvest1801.invest.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;
import com.p2p.bawei.p2pinvest1801.invest.mvp.contract.InvestContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InvestPresenter extends BasePresenter<InvestContract.IInvestContractModel,InvestContract.IInvestContractView> {
    public InvestPresenter(InvestContract.IInvestContractModel mModel, final InvestContract.IInvestContractView mView) {
        super(mModel, mView);


        mModel.getInvestAllBean(new Observer<InvestAllBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InvestAllBean investAllBean) {
                mView.onInvestAllBean(investAllBean);
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
