package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.bean.MyUploadEntity;

import io.reactivex.Observer;

public interface MyUploadContract {
    interface mModle extends IModel {
        void setupload(Observer<MyUploadEntity> observer);
    }
    interface mView extends IView {
        void setupload(MyUploadEntity uploadEntity);
    }
}
