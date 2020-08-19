package com.p2p.bawei.p2pinvest1801;


import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.LoginBean;
import com.p2p.bawei.p2pinvest1801.user_act.bean.RegisterBean;

import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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
    Observable<LoginBean> getLoginData(@FieldMap TreeMap<String, String> hashMap);

    @POST("register")
    Observable<RegisterBean> getRegisterData(@Query("name") String username, @Query("password") String password);
}
