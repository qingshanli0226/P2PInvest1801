package com.p2p.bawei.p2pinvest1801.app;

import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyApi {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyHomeBean> get_home();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<MyAllBean> get_all();
}
