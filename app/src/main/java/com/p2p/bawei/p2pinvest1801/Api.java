package com.p2p.bawei.p2pinvest1801;


import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<WelComeUpAppBean> getAppData();
    @GET("atguigu/apk/P2PInvest/app_new.apk")
    Observable<RequestBody> downLoadApp();
}
