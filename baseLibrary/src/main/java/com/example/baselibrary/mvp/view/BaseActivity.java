package com.example.baselibrary.mvp.view;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baselibrary.CacheManager;
import com.example.baselibrary.mvp.presenter.IPresenter;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, IActivity, View.OnClickListener {
    protected P mPresenter;
    private long thisTime;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        CacheManager.getCacheManager().removeAct(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initView();
        initPresenter();
        initData();
        CacheManager.getCacheManager().addAct(this);
    }


    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("hq", "showMessage: " + message);
    }


}
