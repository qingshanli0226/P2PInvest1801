package com.p2p.bawei.p2pinvest1801.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.Cache;

import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.view.fragment.HomeFragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private static CacheManager cacheManager;
    private List<HomeBean.ResultBean.ImageArrBean> list = new ArrayList<>();
    private List<HomeBean.ResultBean> list_homebean = new ArrayList<>();
    private List<HomeBean.ResultBean.ProInfoBean> list_proinfobean = new ArrayList<>();
    HomeFragment homeFragment = new HomeFragment();
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
                .delay(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        homeFragment.hideLoading();
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        homeFragment.showLoading();
                    }
                })
                .subscribe(new BaseObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        add(homeBean.getResult());
                        Log.i("----", homeBean.getCode()+"");
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
        list_proinfobean.add(homeBean.getProInfo());
    }
    public List<HomeBean.ResultBean.ProInfoBean> getList_proinfobean(){
        return list_proinfobean;
    }
}
