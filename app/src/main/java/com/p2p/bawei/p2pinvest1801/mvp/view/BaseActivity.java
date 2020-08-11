package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.p2p.bawei.p2pinvest1801.mvp.presenter.IPresenter;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView,IActivity, View.OnClickListener {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(banLayout());
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public void showMessage(String message) {
        Log.d("ljl", "showMessage: "+message);
    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }
}
