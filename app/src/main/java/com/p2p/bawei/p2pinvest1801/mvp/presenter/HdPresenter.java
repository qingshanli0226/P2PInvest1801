package com.p2p.bawei.p2pinvest1801.mvp.presenter;

import com.bw.framwork.presenter.BasePresenter;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;
import com.bw.net.callback.MyCallBack;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HdContract;

public class HdPresenter extends BasePresenter<HdContract.Model,HdContract.View> {
    public HdPresenter(HdContract.Model mModel, HdContract.View mView) {
        super(mModel, mView);
    }

    public void homeData(){
        mModel.getHomeData(new MyCallBack<HomeBean>() {
            @Override
            public void onNext(HomeBean homeBean) {
                super.onNext(homeBean);
                if (homeBean!=null){
                    mView.setHomeData(homeBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    public void upDate(){
        mModel.getUpdateBean(new MyCallBack<UpdataBean>() {
            @Override
            public void onNext(UpdataBean updataBean) {
                super.onNext(updataBean);
                if (updataBean!=null){
                    mView.setUpdateBean(updataBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
