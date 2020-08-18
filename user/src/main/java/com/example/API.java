package com.example;

import com.example.bean.LoginBean;
import com.example.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface API {
    @POST("login")
    Observable<LoginBean> getLoginData(@Field("name") String username, @Field("password") String password);

    @POST("register")
    Observable<RegisterBean> getRegisterData(@Field("name") String username, @Field("password") String password);
}
