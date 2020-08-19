package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;

import io.reactivex.Observer;

public interface MyRegisterContract  {
    interface mModle extends IModel {
        void getregister(String name,String paw,Observer<MyRegisterEntity> observer);
    }
    interface mView extends IView {
        void getregister(MyRegisterEntity myRegisterEntity);
        String getusername();
        String getpaw();
    }
}
