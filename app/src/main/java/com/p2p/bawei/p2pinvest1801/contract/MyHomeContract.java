package com.p2p.bawei.p2pinvest1801.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;

public interface MyHomeContract {

    interface View extends IView{
        void initAdapter(MyHomeBean myHomeBean);
    }

    interface Model extends IModel{
        void request_home(BaseObserver<MyHomeBean> observer);
    }
}
