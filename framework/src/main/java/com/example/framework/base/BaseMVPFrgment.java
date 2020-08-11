package com.example.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;


//定义抽象的MVPFragment类，使用这个类代表的是该fragment需要网络请求
public abstract class BaseMVPFrgment<T extends IPresenter,V extends IView> extends BaseFragment {
    protected T iHttpPresenter;
    protected ProgressBar loadingBar;//基类来定义加载的UI的形式
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        iHttpPresenter.attachView();
        initHttpData();


    }

    protected abstract void initHttpData();

    protected abstract void initPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        iHttpPresenter.detachView();
    }
}
