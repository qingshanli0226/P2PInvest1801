package com.example.net;

import android.content.SharedPreferences;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static InvestApiService investApiService;

    public static InvestApiService getInvestApiService() {
        if (investApiService == null) {
            investApiService = create();
        }
        return investApiService;
    }

    private static InvestApiService create() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new MyHttpLoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InvestConstant.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(InvestApiService.class);
    }

    static class MyHttpLoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            SharedPreferences sharedPreferences = CacheManager.getCacheManager().getSharedPreferences();

            String token = sharedPreferences.getString(InvestConstant.SP_TOKEN, "");

            Request request = chain.request();

            Request token1 = request.newBuilder().addHeader(InvestConstant.SP_TOKEN, token).build();

            return chain.proceed(token1);
        }
    }

}
