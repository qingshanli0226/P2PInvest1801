package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.bw.framwork.model.BaseModel;
import com.bw.net.RetrofitManager;
import com.bw.net.SpManager;
import com.bw.net.api.Api;
import com.bw.net.bean.OutLoginBean;
import com.bw.net.bean.UploadBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.UploadContract;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadModel extends BaseModel implements UploadContract.Model {
    @Override
    public void goUpload(MyCallBack<UploadBean> callBack) {

        File file = new File(SpManager.getInstance().getContents("imagePath"));
        RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("picture", file.getName(), fileRQ);

        RetrofitManager.getInstance()
                .retrofit
                .create(Api.class)
                .uploadFile(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }

    @Override
    public void outLogin(MyCallBack<OutLoginBean> callBack) {
        RetrofitManager.getInstance()
                .retrofit
                .create(Api.class)
                .outLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack);
    }
}
