package com.p2p.bawei.p2pinvest1801.user.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;

import io.reactivex.Observer;

public interface LoginContract {

    interface ILoginContractView extends IView {

        void onLogin(LoginBean stringBean);

    }

    interface ILoginContractModel extends IModel {

        void getLogin(String name, String password, Observer<LoginBean> observer);

    }


}
