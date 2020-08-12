package com.p2p.bawei.p2pinvest1801.cache;

import android.content.Context;
import android.util.Log;

import androidx.constraintlayout.solver.Cache;

import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private static CacheManager cacheManager;
    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    public synchronized static CacheManager getInstance(){
        if(cacheManager == null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }
    public void init(Context context){

    }
    public void initInter(){
        RetrofitManager.getInstance().getRetrofit()
                .create(P2PApi.class)
                .homelist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        add(homeBean.getResult());
                    }

                    @Override
                    public void onRequestError(String code, String message) {

                    }
                });
    }
    public List<HomeBean.ResultBean.ImageArrBean> getList(){
        return list;
    }
    public void add(HomeBean.ResultBean homeBean){
        list.addAll(homeBean.getImageArr());
    }
}
