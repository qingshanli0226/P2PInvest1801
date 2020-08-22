package com.example.net;


import com.example.net.bean.BaseBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface InvestApiService {

    //主页面数据
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    //投资全部理财数据
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean> getProductData();

    //用户注册
    @POST("register")
    @FormUrlEncoded
    Observable<BaseBean<String>> onUserRegister(@FieldMap Map<String, String> params);

    //用户登录
    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> onUserLogin(@FieldMap Map<String,String> params);

    //用户自动登录


    //定义下载文件
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);
}
