package com.example.lib_core.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Http {

    private static Http instance;

    public static Http getInstance(){
        if (instance == null){
            instance = new Http();
        }
        return instance;
    }


    private Retrofit retrofit;

    public Retrofit creatRetrofit(){
        if (retrofit == null){
            //创建retrofit
            create();
        }
        return retrofit;
    }


    private void create(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://49.233.93.155:8080/");
        builder.client(client);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();

    }

}
