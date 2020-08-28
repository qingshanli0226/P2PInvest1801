package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.bw.framwork.model.IModel;
import com.bw.framwork.view.IView;
import com.bw.net.bean.LoginBean;
import com.bw.net.callback.MyCallBack;

public interface LoginContract {

    interface View extends IView{
        String getname();

        String getpwd();

        void loginOk(LoginBean loginBean);

    }

    interface Model extends IModel{

        void login(String name, String pwd, MyCallBack<LoginBean> callBack);
    }
}
