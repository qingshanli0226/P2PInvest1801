package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.InvestBean;
import com.example.net.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface P2PApi {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> homelist();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> versionlist();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestBean> investlist();
}
