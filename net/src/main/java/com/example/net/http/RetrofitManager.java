package com.example.net.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager retrofitManager;
    public static RetrofitManager getInstance(){
        if(retrofitManager==null){
        retrofitManager=new RetrofitManager();
        }
        return retrofitManager;
    }

    private String baseurl="http://49.233.93.155:8080";

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    private Retrofit retrofit;
    public Retrofit getRetrofit() {
        if(retrofit==null){
            create();
        }
        return retrofit;
    }

    private void create() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        retrofit=new Retrofit.Builder().client(client).baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }
}
