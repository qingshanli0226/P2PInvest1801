package com.example.net;

import com.example.common.bean.BaseBean;

import io.reactivex.functions.Function;

public class NetFunction<R extends BaseBean<T>, T> implements Function<R,T> {
    @Override
    public T apply(R r) throws Exception {
        if(r.getCode().equals("200")){
            return r.getResult();
        }else{
            throw new NetException(r.getCode(), r.getMessage());
        }
    }
}
