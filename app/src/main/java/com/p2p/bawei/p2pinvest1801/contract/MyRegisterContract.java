package com.p2p.bawei.p2pinvest1801.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;

public interface MyRegisterContract {

    interface View extends IView {
        String getUsername();
        String getPwd();
        void goMain(RegisterBean registerBean);
    }

    interface Model extends IModel {
        void request_register(String username,String pwd,BaseObserver<RegisterBean> observer);
    }
}
