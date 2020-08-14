package com.p2p.bawei.p2pinvest1801.contract;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.model.IModel;
import com.bw.lib_core.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;

public interface MyAllContract {

    interface View extends IView{
        void initAdapter(MyAllBean myAllBean);
    }

    interface Model extends IModel{
        void request_all(BaseObserver<MyAllBean> observer);
    }
}
