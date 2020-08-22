package com.p2p.bawei.p2pinvest1801.app;

import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApi {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyHomeBean> get_home();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<MyAllBean> get_all();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> get_register(@FieldMap Map<String,String> map);

    @GET("login")
    Observable<RegisterBean> get_login();
}
