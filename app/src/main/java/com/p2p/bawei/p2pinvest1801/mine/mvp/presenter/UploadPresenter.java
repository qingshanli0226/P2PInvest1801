package com.p2p.bawei.p2pinvest1801.mine.mvp.presenter;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.mine.mvp.contract.UploadContract;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadPresenter extends BasePresenter<UploadContract.IUploadContractMolde,UploadContract.IUploadContractView> {

    public UploadPresenter(UploadContract.IUploadContractMolde mModel, UploadContract.IUploadContractView mView) {
        super(mModel, mView);
    }

    public void upLoadImg(String path) {
        mModel.getPic(new Observer<StringBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StringBean stringBean) {
                mView.onPic(stringBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, path);

    }

}
