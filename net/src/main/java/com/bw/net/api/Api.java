package com.bw.net.api;

import com.bw.net.bean.AllLCBean;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeBean();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdataBean> getUpdataBean();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllLCBean> getAllLCBean();
}
