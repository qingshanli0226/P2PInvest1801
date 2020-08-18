package com.p2p.bawei.p2pinvest1801.frist.contract;


import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;

public interface FirstContract {
    interface View extends IView {
        void upData(FirstBean firstBean);
    }

    interface Model extends IModel {
        void getFirstData(BaseObserver<FirstBean> beanBaseObserver);
    }
}
