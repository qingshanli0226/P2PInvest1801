package com.p2p.bawei.p2pinvest1801.mvp.api;

import com.p2p.bawei.p2pinvest1801.bean.EdtivitnEnitiy;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;
import com.p2p.bawei.p2pinvest1801.bean.MyRegisterEntity;
import com.p2p.bawei.p2pinvest1801.bean.MyUploadEntity;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyBannerEntity> getbanner();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<EdtivitnEnitiy> getedtivity();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<MyInestEntivity> getintivity();
    @POST("register")
    @FormUrlEncoded
    Observable<MyRegisterEntity> getregister(@FieldMap Map<String,String> params);
    @POST("upload")
    @FormUrlEncoded
    Observable<MyUploadEntity>setupload(@FieldMap Map<String,String> upmap);

}
