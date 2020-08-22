package com.p2p.bawei.p2pinvest1801.mvp.presenter;


import com.example.lib_core.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestmentAllFinancialContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InvestmentAllFinancialPresenter extends BasePresenter<InvestmentAllFinancialContract.Model,InvestmentAllFinancialContract.View> {
    public InvestmentAllFinancialPresenter(InvestmentAllFinancialContract.Model mModel, InvestmentAllFinancialContract.View mView) {
        super(mModel, mView);
    }

    public void getDta(){
        mModel.getData(new Observer<InvestmentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InvestmentBean investmentBean) {
                if (mView!=null){
                    mView.initAllData(investmentBean);
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
