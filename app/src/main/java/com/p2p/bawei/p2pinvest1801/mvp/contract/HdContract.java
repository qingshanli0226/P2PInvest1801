package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.bw.framwork.model.IModel;
import com.bw.framwork.view.IView;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;
import com.bw.net.callback.MyCallBack;

public interface HdContract {

    interface View extends IView{
        void setHomeData(HomeBean homeData);
        void setUpdateBean(UpdataBean updateBean);

    }

    interface Model extends IModel{
        void getHomeData(MyCallBack<HomeBean> callBack);

        void getUpdateBean(MyCallBack<UpdataBean> callBack);
    }
}
