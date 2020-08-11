package com.example.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;



import com.example.framework.R;

public abstract class BaseMVPActivity<T extends IPresenter,V extends IView> extends BaseActivity {
    protected T isHttpPresenter;
    protected ProgressBar loadingBar;   //用来定义加载UI的形式

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //在framwork里定义这个loadingbar控件，只是为了让编译器通过检查
        loadingBar = findViewById(R.id.loadingBar);
        initPresenter();
        initData();

    }

    protected abstract void initData();

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isHttpPresenter.detachView();
    }
}
