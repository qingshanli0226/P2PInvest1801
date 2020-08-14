package com.p2p.bawei.p2pinvest1801.presenter;

import com.bw.lib_core.http.BaseObserver;
import com.bw.lib_core.presenter.BasePresenter;
import com.p2p.bawei.p2pinvest1801.bean.MyAllBean;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyAllContract;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;

public class MyAllPresenter extends BasePresenter<MyAllContract.Model,MyAllContract.View> {
    public MyAllPresenter(MyAllContract.Model mModel, MyAllContract.View mView) {
        super(mModel, mView);
    }

    public void get_all(){
        mModel.request_all(new BaseObserver<MyAllBean>() {
            @Override
            public void success(MyAllBean mybean) {
                mView.initAdapter(mybean);
            }
        });
    }
}
