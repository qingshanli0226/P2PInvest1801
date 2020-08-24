package com.p2p.bawei.p2pinvest1801.user_act.contract;

import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;

public interface RegisterContract {
    interface View extends IView {
        String getUserName();

        String getReUserPWD();

        String getUserPWD();

        void goMain();
    }

    interface Model extends IModel {
        void getData(BaseObserver<RegisterBean> baseObserver, String name, String pwd);
    }
}
