package com.p2p.bawei.p2pinvest1801.api;

import com.bw.lib_core.bean.BaseBean;
import com.p2p.bawei.p2pinvest1801.bean.AutoLoginBean;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.bean.MyUploadBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface MyApi {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyHomeBean> get_home();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<MyAllBean> get_all();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> get_register(@FieldMap Map<String,String> map);

    @POST("login")
    Observable<LoginBean> get_login(@FieldMap HashMap<String,String> map);

    @POST("autoLogin")
    Observable<LoginBean> get_autoLogin(@FieldMap HashMap<String,String> map);

    @Multipart
    @POST("upload")
    Observable<MyUploadBean> get_Upload();

    @POST("crash")
    @FormUrlEncoded
    Observable<BaseBean<String>> crashReport(@FieldMap HashMap<String,String> params);
}
