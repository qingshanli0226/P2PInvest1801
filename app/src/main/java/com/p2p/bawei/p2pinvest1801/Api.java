package com.p2p.bawei.p2pinvest1801;


import com.example.net.BaseBean;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;

import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<WelComeUpAppBean> getAppData();

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<FirstBean> getFirstData();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestBean> getInvestData();

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLoginData(@FieldMap TreeMap<String, String> treeMap);

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegisterData(@FieldMap TreeMap<String, String> treeMap);

    @FormUrlEncoded
    @POST("crash")
    Observable<BaseBean<String>> upData(@FieldMap HashMap<String, String> params);
}
