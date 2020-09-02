package com.p2p.bawei.p2pinvest1801.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;

public interface MyLoginContract {

    interface View extends IView {
        String getUsername();
        String getPwd();
        void goMain(LoginBean loginBean);
    }

    interface Model extends IModel {
        void request_login(String username, String pwd, BaseObserver<LoginBean> observer);
    }
}
