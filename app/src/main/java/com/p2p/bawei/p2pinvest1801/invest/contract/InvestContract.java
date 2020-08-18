package com.p2p.bawei.p2pinvest1801.invest.contract;


import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;

public interface InvestContract {
    interface View extends IView {
        void upDate(InvestBean investBean);
    }

    interface Model extends IModel {
        void getInvestData(BaseObserver<InvestBean> baseObserver);
    }
}
