package com.p2p.bawei.p2pinvest1801.welcome.center;

import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.mvp.model.IModel;
import com.p2p.bawei.p2pinvest1801.mvp.view.IView;


public interface WelContract {
    interface View extends IView {
        void upAppCode(WelComeUpAppBean.ResultBean str);
    }

    interface Model extends IModel {
        void getAppCode(BaseObserver<WelComeUpAppBean> baseObserver);

    }
}
