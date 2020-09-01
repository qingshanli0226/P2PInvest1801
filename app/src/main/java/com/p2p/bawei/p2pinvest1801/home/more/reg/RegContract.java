package com.p2p.bawei.p2pinvest1801.home.more.reg;

import com.example.framework2.mvp.model.IModel;
import com.example.framework2.mvp.view.IView;
import com.example.net.activity_bean.LoginBean;
import com.example.net.activity_bean.RegisterBean;

import io.reactivex.Observer;

public interface RegContract {
    interface View extends IView {
        String getUserName();
        String getUserPwd();
        void regOk();
        void show(String msg);
    }
    interface Model extends IModel{
        void requestReg(String name, String pwd, Observer<RegisterBean> observer);
    }
}
