package com.p2p.bawei.p2pinvest1801.invest.all.presenter;

import com.example.net.RetrofitManager;
import com.example.net.bean.ProductBean;
import com.p2p.bawei.p2pinvest1801.invest.all.contract.ProductContract;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductPresenterImpl extends ProductContract.ProductPresenter {
    @Override
    public void getProductData() {
        RetrofitManager.getInvestApiService()
                .getProductData()
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) {
                        if (iHttpView == null){
                            return;
                        }
                        iHttpView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() {
                        if (iHttpView == null){
                            return;
                        }
                        iHttpView.hideLoading();
                    }
                })
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        if (iHttpView == null) {
                            return;
                        } else if (productBean.getCode() == 200) {
                            iHttpView.onProductData(productBean);
                        } else {
                            iHttpView.showError("ERROR" + productBean.getCode(), productBean.getMsg());
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
