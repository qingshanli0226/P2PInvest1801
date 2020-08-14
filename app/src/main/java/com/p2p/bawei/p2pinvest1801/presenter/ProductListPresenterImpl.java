package com.p2p.bawei.p2pinvest1801.presenter;

import com.example.net.FinanceManager;
import com.example.net.mode.InvestListBean;
import com.p2p.bawei.p2pinvest1801.contract.ProductListContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenterImpl extends ProductListContract.ProductListPresenter {
    @Override
    public void onGetListData() {
        FinanceManager.getInstance().getFinanceApi()
                .getInvestListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InvestListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InvestListBean investListBean) {
                        if(investListBean.getCode() == 200){
                            iHttpView.onListData(investListBean);
                        } else{
                            iHttpView.showError(investListBean.getCode()+"",investListBean.getMsg());
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
