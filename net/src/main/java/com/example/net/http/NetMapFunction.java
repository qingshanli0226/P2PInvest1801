package com.example.net.http;


import com.example.net.activity_bean.BaseBean;

import io.reactivex.functions.Function;


public class NetMapFunction<T extends BaseBean<R>, R> implements Function<T, R> {
    @Override
    public R apply(T t) throws Exception {
        if (t.getCode().equals("200")) {
            return t.getResult();//1，如果服务端正确处理服务请求，会将服务端返回的数据类型由BaseBean转换成ResultBean
        } else {
            //2,当服务端，没有处理该请求，返回业务错误时,抛出业务类型到的异常,该异常错误将会进入到onError方法中处理
            throw new NetBusinessException(t.getCode(), t.getMessage());//在此处抛出的异常会进入到RxJava的onError方法中
        }
    }
}
