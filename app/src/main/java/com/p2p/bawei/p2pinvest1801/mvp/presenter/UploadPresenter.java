package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.bw.framwork.presenter.BasePresenter;
import com.bw.net.bean.OutLoginBean;
import com.bw.net.bean.UploadBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;

public class UploadPresenter extends BasePresenter<UploadContract.Model,UploadContract.View> {

    public UploadPresenter(UploadContract.Model mModel, UploadContract.View mView) {
        super(mModel, mView);
    }

    public void upload(){
        mModel.goUpload(new MyCallBack<UploadBean>() {
            @Override
            public void onNext(UploadBean uploadBean) {
                super.onNext(uploadBean);
                if (uploadBean!=null){
                    mView.uploadOk(uploadBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    public void outlogin(){
        mModel.outLogin(new MyCallBack<OutLoginBean>() {
            @Override
            public void onNext(OutLoginBean outLoginBean) {
                super.onNext(outLoginBean);
                if (outLoginBean.getCode().equals("200")){
                    mView.outLogin(outLoginBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

}
