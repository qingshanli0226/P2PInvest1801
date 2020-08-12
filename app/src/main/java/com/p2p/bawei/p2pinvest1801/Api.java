package com.p2p.bawei.p2pinvest1801;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<String> getAppData();
}
