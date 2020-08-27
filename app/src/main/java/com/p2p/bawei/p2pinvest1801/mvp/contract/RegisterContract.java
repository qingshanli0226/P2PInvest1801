package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;

import io.reactivex.Observer;

public interface RegisterContract {

    interface View extends IView{
        void success();
    }

    interface Model extends IModel{
        void setRegister(String name,String pwd,String phone,String rePwd,Observer<RegisterBean> observer);
    }

}
