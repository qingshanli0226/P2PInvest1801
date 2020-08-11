package com.p2p.bawei.p2pinvest1801.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static HttpUtils httpUtils;
    private Retrofit retrofit;
    private String url;

    public static HttpUtils getHttpUtiles() {
        if (httpUtils == null){
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null){
            create();
        }
        return retrofit;
    }

    private void create() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(60000, TimeUnit.SECONDS)
                .writeTimeout(60000, TimeUnit.SECONDS)
                .connectTimeout(60000, TimeUnit.SECONDS)
                .build();
        Retrofit build = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit = build;
    }
}
