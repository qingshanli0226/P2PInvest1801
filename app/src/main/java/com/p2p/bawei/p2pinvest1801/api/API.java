package com.p2p.bawei.p2pinvest1801.api;

import com.p2p.bawei.p2pinvest1801.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeBannerData ();

}
