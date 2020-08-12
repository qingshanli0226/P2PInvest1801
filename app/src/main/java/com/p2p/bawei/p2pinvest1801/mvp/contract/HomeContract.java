package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;

import io.reactivex.Observer;

public interface HomeContract {

    interface IHomeContractView extends IView {
       void onHomeBean(HomeBean homeBean);
    }

    interface IHomeContractModle extends IModel {
        void getHomeBean(Observer<HomeBean> observer);
    }

}
