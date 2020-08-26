package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.example.common.bean.UploadBean;
import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.BaseObserver;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;

public class UploadPresenter extends BasePresenter<UploadContract.UploadView,UploadContract.UploadModel> {
    public UploadPresenter(UploadContract.UploadModel mModel, UploadContract.UploadView mView) {
        super(mModel, mView);
    }
    public void uploadP(){
        mModel.requestupload(mView.url(),new BaseObserver<UploadBean>() {
            @Override
            public void onNext(UploadBean uploadBean) {
                mView.uploadView(uploadBean);
            }

            @Override
            public void onRequestError(String code, String message) {

            }
        });
    }
}
