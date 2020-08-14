package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.example.net.BaseObserver;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;

public interface InvestContract {
    interface InvestModel extends IModel{
        void requestInvest(BaseObserver<InvestBean> observer);

    }
    interface InvestView extends IView {
        void invest(InvestBean investbean);
    }
}
