package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;

import io.reactivex.Observer;

public interface HomeContract {

    interface View extends IView{
        void initHomeData(HomeBean homeBean);
    }

    interface Model extends IModel{
        void getData(Observer<HomeBean> observer);
    }

}
