package com.p2p.bawei.p2pinvest1801.user_act.contract;

import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;

public interface RegisterContract {
    interface View extends IView {
        String getUserName();

        String getUserPWD();

        void goMain();
    }

    interface Model extends IModel {
        void getData(BaseObserver<LoginBean> baseObserver);
    }
}
