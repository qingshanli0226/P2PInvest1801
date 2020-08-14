package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.framwork.mvp.model.BaseModel;
import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.BaseObserver;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.InvestContract;

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
        });
    }
}
