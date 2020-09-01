package com.p2p.bawei.p2pinvest1801.mine.mvp.model;

import android.util.Log;

import com.example.framwork.mvp.model.BaseModel;
import com.example.net.NetRetrofitManager;
import com.p2p.bawei.p2pinvest1801.api.Api;
import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.mine.mvp.contract.UploadContract;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadModel extends BaseModel implements UploadContract.IUploadContractMolde {


    @Override
    public void getPic(Observer<StringBean> observer,String path) {
        Log.i("onPic", "getPic: "+path);
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("headimg", file.getName(), requestFile);
        NetRetrofitManager.getInstance().getRetrofit().create(Api.class).upload(filePart)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }


}
