package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.bean.UploadBean;
import com.example.framwork.mvp.model.BaseModel;
import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UploadModel extends BaseModel implements UploadContract.UploadModel{
    @Override
    public void requestupload(String url,BaseObserver<UploadBean> observer) {

        File file = new File(url);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RetrofitManager.getInstance()
                .getRetrofit()
                .create(P2PApi.class)
                .uploadFile(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
