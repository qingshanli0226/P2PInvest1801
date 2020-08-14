package com.p2p.bawei.p2pinvest1801.frist.contract;

import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.mvp.model.IModel;
import com.p2p.bawei.p2pinvest1801.mvp.view.IView;

public interface FirstContract {
    interface View extends IView {
        void upData(FirstBean firstBean);
    }

    interface Model extends IModel {
        void getFirstData(BaseObserver<FirstBean> beanBaseObserver);
    }
}
