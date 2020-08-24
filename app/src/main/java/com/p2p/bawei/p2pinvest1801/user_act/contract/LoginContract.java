package com.p2p.bawei.p2pinvest1801.user_act.contract;

import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;

public interface LoginContract {
    interface View extends IView {
        String getUserName();

        String getUserPWD();

        void goMain(LoginBean.ResultBean loginBean);
    }

    interface Model extends IModel {
        void getData(BaseObserver<LoginBean> baseObserver, String name, String pwd);
    }
}
