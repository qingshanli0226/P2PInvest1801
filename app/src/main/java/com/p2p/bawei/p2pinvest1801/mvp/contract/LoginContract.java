package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;

import io.reactivex.Observer;

public interface LoginContract {

    interface View extends IView{
        void success();//登录成功
    }

    interface Model extends IModel{
        void setLogin(String name, String pwd, Observer<LoginBean> observer);
    }


}
