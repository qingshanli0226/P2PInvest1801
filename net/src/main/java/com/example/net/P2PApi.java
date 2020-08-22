package com.example.net;

import com.example.net.bean.BaseBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;
import com.example.net.bean.VersionBean;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
