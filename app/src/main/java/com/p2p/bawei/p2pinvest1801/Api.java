package com.p2p.bawei.p2pinvest1801;


import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.bean.InvestBean;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<WelComeUpAppBean> getAppData();

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<FirstBean> getFirstData();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<InvestBean> getInvestData();
}
