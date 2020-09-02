package com.bw.lib_core.http;

import android.app.Activity;

import com.google.gson.Gson;
import com.p2p.bawei.p2pinvest1801.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofitManager {

    private List<Activity> activityList = new ArrayList<>();//存储所有在后台的Activity实例，便于结束进程
    private static volatile HttpRetrofitManager httpRetrofitManager;
    public static synchronized HttpRetrofitManager getInstance(){
        if(httpRetrofitManager==null){
            httpRetrofitManager = new HttpRetrofitManager();
        }
        return httpRetrofitManager;
    }

    private Retrofit retrofit;
    private Gson gson;

    public Retrofit getRetrofit(){
        if(retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                     .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5,TimeUnit.SECONDS)
                    .writeTimeout(5,TimeUnit.SECONDS)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://49.233.93.155:9999/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client);
            retrofit = builder.build();
        }
        return retrofit;
    }

    public Gson getGson(){
        if(gson==null){
            gson = new Gson();
        }
        return gson;
    }

    //定义接口，当购物车数据发生改变时，通过该接口通知页面刷新
    public interface IShopcarDataChangeListener {
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position, ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }


    public void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
