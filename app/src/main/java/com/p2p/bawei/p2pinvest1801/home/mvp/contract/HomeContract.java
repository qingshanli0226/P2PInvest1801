package com.p2p.bawei.p2pinvest1801.home.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;

import io.reactivex.Observer;

public interface HomeContract {

    interface IHomeContractView extends IView {
       void onHomeBean(HomeBean homeBean);
       void onUpDateBean(UpDateBean upDateBean);
    }

    interface IHomeContractModle extends IModel {
        void getHomeBean(Observer<HomeBean> observer);
        void getUpDateBean(Observer<UpDateBean> observer);
    }

}
