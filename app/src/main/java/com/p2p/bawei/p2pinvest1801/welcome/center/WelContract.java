package com.p2p.bawei.p2pinvest1801.welcome.center;

import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;


public interface WelContract {
    interface View extends IView {
        void upAppCode(WelComeUpAppBean.ResultBean str);
    }

    interface Model extends IModel {
        void getAppCode(BaseObserver<WelComeUpAppBean> baseObserver);

    }
}
