package com.example.net.api_srever;

import com.example.net.activity_bean.IndexBean;
import com.example.net.activity_bean.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<IndexBean> getIndex();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdateBean> getVersion();
}
