package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import android.util.Log;

import com.example.lib_core.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.baseview.BaseProgressView;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestmentAllFinancialContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.InvestmentAllFinancialModel;

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
                    Log.e("InvestmentAllFinancialPresenter -> getDta -> onNext", "onNext: " );
                    mView.initAllData(investmentBean);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("InvestmentAllFinancialPresenter -> getDta -> onError", "onNext: " );
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
