package com.example.net;


import com.example.net.bean.HomeBean;
import com.example.net.bean.ProductBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface InvestApiService {

    //主页面数据
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    //投资全部理财数据
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean> getProductData();


}
