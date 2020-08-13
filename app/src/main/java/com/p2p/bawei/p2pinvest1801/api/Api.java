package com.p2p.bawei.p2pinvest1801.api;

import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> homeBeans();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpDateBean> upDateBeans();

}
