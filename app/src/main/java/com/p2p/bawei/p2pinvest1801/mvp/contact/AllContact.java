package com.p2p.bawei.p2pinvest1801.mvp.contact;

import com.example.mylibrary.mvp.model.IModel;
import com.example.mylibrary.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.AllBean;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;

import io.reactivex.Observer;

public interface AllContact {
    interface Model extends IModel {
        void requestAll(Observer<AllBean> observer);
    }
    interface View extends IView {
        void initAll(AllBean allBean);

    }
}
