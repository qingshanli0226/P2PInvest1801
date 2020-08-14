package com.p2p.bawei.p2pinvest1801.api;

import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.invest.bean.InvestAllBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> homeBeans();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpDateBean> upDateBeans();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestAllBean> investAllBeans();
}
