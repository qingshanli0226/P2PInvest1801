package com.p2p.bawei.p2pinvest1801.api;

import com.p2p.bawei.p2pinvest1801.bean.StringBean;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;
import com.p2p.bawei.p2pinvest1801.user.bean.LoginBean;

import java.util.HashMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> homeBeans();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpDateBean> upDateBeans();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestAllBean> investAllBeans();

    @POST("register")
    @FormUrlEncoded
    Observable<StringBean> registerBeans(@FieldMap TreeMap<String, String> map);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> loginBeans(@FieldMap TreeMap<String, String> map);

    //定义下载文件
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

    @GET("findFocusVideo")
    Observable<AboutFinanceBean> getAlbumFromServer();

    @Multipart
    @POST("upload")
    Observable<StringBean> upload(@Part MultipartBody.Part part);


    @POST("crash")
    @FormUrlEncoded
    Observable<String> crashReport(@FieldMap HashMap<String,String> params);
}
