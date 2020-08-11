package com.example.framework.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMVPFragment<P extends IPresenter,V extends IView> extends BaseFragment {

    protected P iHttpPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        iHttpPresenter.attachView((IView) this);
        initGetData();
    }

    protected abstract void initGetData();

    protected abstract void initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(iHttpPresenter != null){
            iHttpPresenter.detachView();
        }
    }
}
