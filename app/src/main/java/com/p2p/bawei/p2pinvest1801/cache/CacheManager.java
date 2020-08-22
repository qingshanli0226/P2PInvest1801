package com.p2p.bawei.p2pinvest1801.cache;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.rtp.RtpStream;
import android.os.Handler;
import android.os.Looper;
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
    private List<IDataChangeListener> dataChangeListenerList = new ArrayList<>();
    public synchronized static CacheManager getInstance(){
        if(cacheManager == null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }
    private Context context;
    public void init(Context context){
        this.context = context;
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
                        list_homebean.add(homeBean.getResult());
                        notifyShopcarDataChanged();
                    }
                    @Override
                    public void onRequestError(String code, String message) {

                    }
                });
    }
    public List<HomeBean.ResultBean.ImageArrBean> getList(){
        return list;
    }
    public void add(){
        list.addAll(list_homebean.get(0).getImageArr());
        list_proinfobean.add(list_homebean.get(0).getProInfo());
    }
    public List<HomeBean.ResultBean.ProInfoBean> getList_proinfobean(){
        return list_proinfobean;
    }

    public void registerDataChangeListener(IDataChangeListener listener) {
        if (!dataChangeListenerList.contains(listener)) {
            dataChangeListenerList.add(listener);
        }
    }
    private void notifyShopcarDataChanged() {
        for(IDataChangeListener listener:dataChangeListenerList) {
            listener.onChange();
        }
    }
    public void unRegisterDataChangeListener(IDataChangeListener listener) {
        if (!dataChangeListenerList.contains(listener)) {
            dataChangeListenerList.remove(listener);
        }
    }


    public interface IDataChangeListener{
        void onChange();
    }
}
