package com.p2p.bawei.p2pinvest1801;


import com.p2p.bawei.p2pinvest1801.bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("ios/cf/dish_list.php")
    Observable<FoodBean> getData(@Query("stage_id") String stage_id, @Query("limit") String limit, @Query("page") String page);
}
