package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.common.view.MyLoadingBar;

public abstract class BaseMVPActivity<T extends IPresenter, V extends IView> extends BaseActivity {

    protected T iHttpPresenter;
    protected MyLoadingBar loadingBar;//基类来定义加载的UI的形式

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        initPresenter();
        iHttpPresenter.attachView((V) this);
        initData();
    }

    protected abstract void initPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        iHttpPresenter.detachView();
    }
}
