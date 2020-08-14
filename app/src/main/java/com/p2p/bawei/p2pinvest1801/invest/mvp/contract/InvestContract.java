package com.p2p.bawei.p2pinvest1801.invest.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;

import io.reactivex.Observer;

public interface InvestContract {

    interface IInvestContractView extends IView {
        void onInvestAllBean(InvestAllBean investAllBean);

    }

    interface IInvestContractModel extends IModel {
        void getInvestAllBean(Observer<InvestAllBean> observer);
    }


}
