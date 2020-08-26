package com.example.net;

import com.example.common.FinanceConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FinanceManager {

    private static FinanceManager financeManager;

    private FinanceManager() {
    }

    public static synchronized FinanceManager getInstance(){
        if(financeManager == null){
            financeManager = new FinanceManager();
        }
        return financeManager;
    }

    private FinanceApi financeApi;

    public FinanceApi getFinanceApi() {
        if(financeApi == null){
            financeApi = create();
        }
        return financeApi;
    }

    private FinanceApi create() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new TokenInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FinanceConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(FinanceApi.class);
    }
}
