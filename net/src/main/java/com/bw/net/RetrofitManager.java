package com.bw.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {  //网络请求框架
    public Retrofit retrofit;
    private RetrofitManager() {
        init();
    }

    private void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(60 * 1000, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.SECONDS)
                .writeTimeout(60 * 1000, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://49.233.93.155:8080")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static RetrofitManager retrofitManager;
    public static RetrofitManager getInstance(){
        if (retrofitManager==null){
            synchronized (String.class){
                if (retrofitManager==null){
                    retrofitManager=new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }
}
