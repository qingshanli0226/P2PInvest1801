package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;

import io.reactivex.Observer;

public interface HomeContract {

    interface View extends IView{
        void initHomeData(HomeBean homeBean);
        void upDate(UpDateBean upDateBean);
    }

    interface Model extends IModel{
        void getData(Observer<HomeBean> observer);
        void getUpdateMsg(Observer<UpDateBean> observer);
    }

}
