package com.p2p.bawei.p2pinvest1801.api;

import com.p2p.bawei.p2pinvest1801.bean.BaseBean;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.InvestmentBean;
import com.p2p.bawei.p2pinvest1801.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.bean.RegisterBean;
import com.p2p.bawei.p2pinvest1801.bean.UpLoadBean;

import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeBannerData ();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestmentBean> InvestmentData();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> Register (@FieldMap TreeMap<String,String> map);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> Login (@FieldMap TreeMap<String,String> map);

    @POST("upload")
    Observable<UpLoadBean> upload();
    @POST("crash")
    @FormUrlEncoded
    Observable<BaseBean<String>> crashReport(@FieldMap HashMap<String,String> params);

}
