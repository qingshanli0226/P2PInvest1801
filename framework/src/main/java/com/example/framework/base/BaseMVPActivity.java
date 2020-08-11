package com.example.framework.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMVPActivity<P extends IPresenter,V extends IView> extends BaseActivity {

    protected P iHttpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        iHttpPresenter.attachView((IView) this);
        initGetData();
    }

    protected abstract void initGetData();

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(iHttpPresenter != null){
            iHttpPresenter.detachView();
        }
    }
}
