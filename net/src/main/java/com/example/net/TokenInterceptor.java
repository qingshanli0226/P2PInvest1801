package com.example.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request build = request.newBuilder().addHeader("token", "159b0a24-969f-4c37-9434-a3e0dde9bc84AND1597968572047").build();

        return chain.proceed(build);
    }
}
