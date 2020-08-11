package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.end_01.mvp.presenter.IPresenter;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView,IActivity, View.OnClickListener {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(banLayout());
        initData();
        initView();
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
