package com.p2p.bawei.p2pinvest1801.home.presenter;

import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.home.contract.HomeContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void getHomeData() {
        RetrofitManager.getInvestApiService()
                .getHomeData()
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run()  {
                        if (iHttpView == null) {
                            return;
                        }
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (iHttpView == null) {
                            return;
                        } else if (homeBean.getCode() == 200) {
                            iHttpView.onHomeData(homeBean);
                        } else {
                            iHttpView.showError("ERROR:" + homeBean.getCode(), homeBean.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
