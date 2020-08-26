package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.bean.LoginBean;
import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.example.net.BaseObserver;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface LoginContract {
    interface LoginView extends IView{
        void loginView(LoginBean loginBean);
        String username();
        String pwd();
    }
    interface LoginModel extends IModel{
        void requestlogin(String username, String pwd, BaseObserver<LoginBean> observer, Consumer<Disposable> consumer);
    }
}
