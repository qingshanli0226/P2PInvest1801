package com.example.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofitManager {

    private static NetRetrofitManager instance;


    Retrofit retrofit;

    public static NetRetrofitManager getInstance() {
        if (instance == null) {
            instance = new NetRetrofitManager();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .baseUrl("http://49.233.93.155:8080")
                    .build();

        }
        return retrofit;
    }
}
