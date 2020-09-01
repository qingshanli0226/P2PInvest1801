package com.p2p.bawei.p2pinvest1801.user.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;

import io.reactivex.Observer;

public interface RegisterContract {

    interface IRegisterContractView extends IView {

        void onRegister(StringBean stringBean);

    }

    interface IRegisterContractModel extends IModel {

        void getRegister(String name, String pwd, Observer<StringBean> observer);

    }


}
