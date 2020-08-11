package com.bw.lib_core.http;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRetrofitManager {

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
                    .baseUrl("http://49.233.93.155:8080/")
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
}
