package com.p2p.bawei.p2pinvest1801.presenter;

import com.example.net.FinanceManager;
import com.example.net.mode.BannerBean;
import com.example.net.mode.VersionBean;
import com.p2p.bawei.p2pinvest1801.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenterImpl extends HomeContract.HomePresenter {
    @Override
    public void onBannerData() {
        FinanceManager.getInstance().getFinanceApi()
                .getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        if(bannerBean.getCode() == 200){
                            iHttpView.onGetBannerData(bannerBean);
                        } else{
                            iHttpView.showError(bannerBean.getCode()+"",bannerBean.getMsg());
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

    @Override
    public void onVersionData() {
        FinanceManager.getInstance().getFinanceApi()
                .getVersionData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        if(versionBean.getCode() == 200){
                            iHttpView.onGetVersionData(versionBean);
                        } else{
                            iHttpView.showError(versionBean.getCode()+"",versionBean.getMsg());
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
