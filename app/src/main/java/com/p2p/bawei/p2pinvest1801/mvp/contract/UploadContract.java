package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.example.common.bean.UploadBean;
import com.example.framwork.mvp.model.IModel;
import com.example.framwork.mvp.view.IView;
import com.example.net.BaseObserver;

public interface UploadContract {
    interface UploadView extends IView {
        void uploadView(UploadBean uploadBean);
        String url();
    }
    interface UploadModel extends IModel {
        void requestupload(String url,BaseObserver<UploadBean> observer);
    }
}
