package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.framwork.mvp.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContract.IHomeContractModle,HomeContract.IHomeContractView> {
    public HomePresenter(HomeContract.IHomeContractModle mModel, final HomeContract.IHomeContractView mView) {
        super(mModel, mView);

        mModel.getHomeBean(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                mView.onHomeBean(homeBean);
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
