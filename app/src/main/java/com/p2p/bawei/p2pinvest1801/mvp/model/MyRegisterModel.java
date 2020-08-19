package com.p2p.bawei.p2pinvest1801.mvp.model;

import com.example.common.http.HttpManager;
import com.google.gson.JsonObject;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.mvp.api.Api;
import com.p2p.bawei.p2pinvest1801.mvp.contract.MyRegisterContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.Field;

public class MyRegisterModel implements MyRegisterContract.mModle {

    @Override
    public void Destory() {

    }

    @Override
    public void getregister(String name, String paw, Observer<MyRegisterEntity> observer) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("password",paw);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        HttpManager.getInstance()
                .getRetrofit()
                .create(Api.class)
                .getregister(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
