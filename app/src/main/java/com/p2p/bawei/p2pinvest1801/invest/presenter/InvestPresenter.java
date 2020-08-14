package com.p2p.bawei.p2pinvest1801.invest.presenter;

import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.invest.contract.InvestContract;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.BasePresenter;

public class InvestPresenter extends BasePresenter<InvestContract.View, InvestContract.Model> {
    public InvestPresenter(InvestContract.View mView, InvestContract.Model mModel) {
        super(mView, mModel);
    }
    public void getData(){
        mModel.getInvestData(new BaseObserver<InvestBean>() {
            @Override
            public void success(InvestBean investBean) {
                mView.upDate(investBean);
            }

            @Override
            public void error(String errorMessage) {

            }
        });
    }
}
