package com.p2p.bawei.p2pinvest1801.mvp.contract;

import com.bw.framwork.model.IModel;
import com.bw.framwork.view.IView;
import com.bw.net.bean.OutLoginBean;
import com.bw.net.bean.UploadBean;
import com.bw.net.callback.MyCallBack;

public interface UploadContract {

    interface View extends IView{
        void uploadOk(UploadBean uploadBean);
        void outLogin(OutLoginBean outLoginBean);
    }

    interface Model extends IModel{
        void goUpload(MyCallBack<UploadBean> callBack);

        void outLogin(MyCallBack<OutLoginBean> callBack);
    }
}
