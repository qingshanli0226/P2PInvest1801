package com.example.framework;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.view.MyLoadingBar;

//定义抽象的MVPFragment类，使用这个类代表的是该Fragment是需要网络请求数据.
public abstract class BaseMVPFragment<T extends IPresenter, V extends IView> extends BaseFragment {

    protected T ihttpPresenter;
    private MyLoadingBar loadingBar;//基类来定义加载的UI的形式

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        initPresenter();
        ihttpPresenter.attachView((V)this);
        initHttpData();
    }

    protected abstract void initHttpData();

    protected abstract void initPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        ihttpPresenter.detachView();
    }
}
