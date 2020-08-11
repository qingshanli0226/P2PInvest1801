package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager retrofitManager;
    public static RetrofitManager getInstance(){
        if(retrofitManager == null){
            synchronized (String.class){
                if(retrofitManager == null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }
    private Retrofit retrofit;
    public Retrofit getRetrofit(){
        if(retrofit == null){
            create();
        }
        return retrofit;
    }

    private void create() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60*1000, TimeUnit.MILLISECONDS)
                .readTimeout(60*1000, TimeUnit.MILLISECONDS)
                .writeTimeout(60*1000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit.Builder retrofits = new Retrofit.Builder();
        retrofits.client(okHttpClient);
        retrofits.baseUrl("http://49.233.93.155:8080/");
        retrofits.addConverterFactory(GsonConverterFactory.create());
        retrofits.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofit = retrofits.build();
    }
}
