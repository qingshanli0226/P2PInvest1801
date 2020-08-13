package com.p2p.bawei.p2pinvest1801.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;

public class MyHomePresenter extends BasePresenter<MyHomeContract.Model,MyHomeContract.View> {
    public MyHomePresenter(MyHomeContract.Model mModel, MyHomeContract.View mView) {
        super(mModel, mView);
    }

    public void get_home(){
        mModel.request_home(new BaseObserver<MyHomeBean>() {
            @Override
            public void success(MyHomeBean mybean) {
                mView.initAdapter(mybean);
            }
        });
    }
}
