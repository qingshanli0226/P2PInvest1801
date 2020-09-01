package com.p2p.bawei.p2pinvest1801.more.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;


import io.reactivex.Observer;

public interface AboutFinanceContract {

    interface IAboutFinanceView extends IView {
        void onAboutFinance(AboutFinanceBean financeBeanList);

    }

    interface IAboutFinanceModel extends IModel {
        void getAboutFinance(Observer<AboutFinanceBean> observer);
    }


}
