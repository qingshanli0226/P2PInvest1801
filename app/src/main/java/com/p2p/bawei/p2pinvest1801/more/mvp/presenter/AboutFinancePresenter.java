package com.p2p.bawei.p2pinvest1801.more.mvp.presenter;

import android.util.Log;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.more.bean.AboutFinanceBean;
import com.p2p.bawei.p2pinvest1801.more.mvp.contract.AboutFinanceContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AboutFinancePresenter extends BasePresenter<AboutFinanceContract.IAboutFinanceModel, AboutFinanceContract.IAboutFinanceView> {


    public AboutFinancePresenter(AboutFinanceContract.IAboutFinanceModel mModel, final AboutFinanceContract.IAboutFinanceView mView) {
        super(mModel, mView);


        mModel.getAboutFinance(new Observer<AboutFinanceBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AboutFinanceBean financeBeanList) {
                Log.i("onAboutFinance", "onNext: " + financeBeanList.getResult().get(0).getVedioUrl());
                Log.i("onAboutFinance", "onNext: " + financeBeanList.getResult().get(0).getUserId());
                Log.i("onAboutFinance", "onNext: " + financeBeanList.getResult().get(0).getCoverImg());

                mView.onAboutFinance(financeBeanList);
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
