package com.example.user.contract;

import com.example.baselibrary.mvp.model.IModel;
import com.example.baselibrary.mvp.view.IView;
import com.example.net.observer.BaseObserver;
import com.example.bean.LoginBean;

public interface LoginContract {
    interface View extends IView {
        String getUserName();

        String getUserPWD();

        void goMain();
    }

    interface Model extends IModel {
        void getData(BaseObserver<LoginBean> baseObserver, String name, String pwd);
    }
}
