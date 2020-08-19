package com.example.common.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    public static HttpManager httpManager;
    public static HttpManager getInstance(){
        if (httpManager==null){
            httpManager=new HttpManager();
        }
        return httpManager;
    }
    private Retrofit retrofit;
    public Retrofit getRetrofit(){
        if (retrofit==null){
            initretrofit();
        }
        return retrofit;
    }

    private void initretrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
         retrofit = new Retrofit.Builder().client(client)
                .baseUrl("http://49.233.93.155:9999/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}
