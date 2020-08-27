package com.example.net;

import com.example.common.bean.BaseBean;
import com.example.common.bean.HomeBean;
import com.example.common.bean.InvestBean;
import com.example.common.bean.LoginBean;
import com.example.common.bean.UploadBean;
import com.example.common.bean.VersionBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface P2PApi {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> homelist();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> versionlist();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestBean> investlist();

    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> register(@FieldMap Map<String, String> params);

    @Multipart
    @POST("upload")
    Observable<UploadBean> uploadFile(@Part("file\"; filename=\"aaa.jpg") RequestBody file);

    @POST("login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@FieldMap HashMap<String, String> params);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> autologin(@FieldMap HashMap<String, String> params);

    @POST("logout")
    Observable<BaseBean<String>> logout();

    @POST("crash")
    @FormUrlEncoded
    Observable<BaseBean<String>> crashReport(@FieldMap HashMap<String,String> params);
}
