package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;

import io.reactivex.Observer;

public interface InvestmentAllFinancialContract {

    interface View extends IView{
        void initAllData(InvestmentBean investmentBean);
    }
    interface Model extends IModel{
        void getData(Observer<InvestmentBean> observer);
    }


}
