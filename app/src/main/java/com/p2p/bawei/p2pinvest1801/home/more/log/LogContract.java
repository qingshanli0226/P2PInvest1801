package com.p2p.bawei.p2pinvest1801.home.more.log;

import com.example.framework2.mvp.model.IModel;
import com.example.framework2.mvp.view.IView;
import com.example.net.activity_bean.LoginBean;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface LogContract {
    interface View extends IView {
        String getUserName();
        String getUserPwd();
        void logOk(LoginBean loginBean);
    }
    interface Model extends IModel{
        void requestLog(String name, String pwd, Observer<LoginBean> observer);
    }
}
