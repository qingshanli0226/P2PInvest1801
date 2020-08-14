package com.p2p.bawei.p2pinvest1801.invest.contract;


import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.mvp.model.IModel;
import com.p2p.bawei.p2pinvest1801.mvp.view.IView;

public interface InvestContract {
    interface View extends IView {
        void upDate(InvestBean investBean);
    }

    interface Model extends IModel {
        void getInvestData(BaseObserver<InvestBean> baseObserver);
    }
}
