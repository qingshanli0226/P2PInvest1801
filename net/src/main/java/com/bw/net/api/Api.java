package com.bw.net.api;

import com.bw.net.bean.AllLCBean;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.OutLoginBean;
import com.bw.net.bean.UpdataBean;
import com.bw.net.bean.UploadBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
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
    Observable<HomeBean> getHomeBean();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdataBean> getUpdataBean();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllLCBean> getAllLCBean();

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("name") String name, @Field("password") String password);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> autoLogin(@Field("token") String token);

    //定义下载文件
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST("upload")
    @Multipart
    Observable<UploadBean> uploadFile(@Part MultipartBody.Part body);

    @POST("crash")
    @FormUrlEncoded
    Observable<UploadBean> crashReport(@FieldMap HashMap<String,String> params);

    @POST("logout")
    Observable<OutLoginBean> outLogin();
}
