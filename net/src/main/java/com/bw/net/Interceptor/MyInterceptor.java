package com.bw.net.Interceptor;

import com.bw.net.SpManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        Request build = builder.addHeader("token", SpManager.getInstance().getContents("token")).build();

        return chain.proceed(build);
    }
}
