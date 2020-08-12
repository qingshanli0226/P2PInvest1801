package com.p2p.bawei.p2pinvest1801.mvp.api;

import com.p2p.bawei.p2pinvest1801.bean.MyBannerEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<MyBannerEntity> getbanner();
}
