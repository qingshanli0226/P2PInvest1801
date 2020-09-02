package com.p2p.bawei.p2pinvest1801.exception;

import com.bw.lib_core.bean.BaseBean;

import io.reactivex.functions.Function;

public class NetFunction<R extends BaseBean<T>,T> implements Function<R,T> {
    @Override
    public T apply(R input)  {

        if(input.getCode().equals(200)){
            return input.getResult();
        }else {
            try {
                throw new  NetBusinessException(input.getCode(), input.getMessage());
            } catch (NetBusinessException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
