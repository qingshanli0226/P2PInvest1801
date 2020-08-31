package com.example.net;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.UserManager;
import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

//通过拦截器，在网络请求头上添加一些自己的东西
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Context context = NetModel.context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("tokens",Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Request request = chain.request();
        Request newRequest = request.newBuilder().addHeader("token",token).build();
        return chain.proceed(newRequest);
    }
}
