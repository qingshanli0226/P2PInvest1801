package com.p2p.bawei.p2pinvest1801.mvp.api;

import com.p2p.bawei.p2pinvest1801.bean.EdtivitnEnitiy;
import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;
import com.p2p.bawei.p2pinvest1801.bean.MyInestEntivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyBannerEntity> getbanner();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<EdtivitnEnitiy> getedtivity();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<MyInestEntivity> getintivity();
}
