package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;

import io.reactivex.Observer;

public interface MyInvestContract {
    interface mModel extends IModel{
        void getinvest(Observer<MyInestEntivity> observer);
    }
    interface mView extends IView {
        void getinvest(MyInestEntivity myInestEntivity);
    }
}
