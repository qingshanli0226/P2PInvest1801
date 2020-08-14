package com.example.net;

import com.example.net.mode.BannerBean;
import com.example.net.mode.InvestListBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FinanceApi {
    //atguigu/json/P2PInvest/index.json
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<BannerBean> getBannerData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestListBean> getInvestListData();
}
