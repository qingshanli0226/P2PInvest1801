package com.example.net;

import com.example.net.mode.AutoLoginBean;
import com.example.net.mode.BannerBean;
import com.example.net.mode.InvestListBean;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.UploadBean;
import com.example.net.mode.UnLoginBean;
import com.example.net.mode.VersionBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FinanceApi {
    //atguigu/json/P2PInvest/index.json
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<BannerBean> getBannerData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestListBean> getInvestListData();

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegisterData(@FieldMap Map<String, String> params);

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@FieldMap Map<String, String> params);

    @POST("logout")
    Observable<UnLoginBean> getLoginOutData();

    @POST("upload")
    @Multipart
    Observable<UploadBean> uploadFile(@Part("file\";filename=\"hj.jpg") RequestBody requestBody);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<AutoLoginBean> autoLogin(@FieldMap Map<String, String> params);

}
