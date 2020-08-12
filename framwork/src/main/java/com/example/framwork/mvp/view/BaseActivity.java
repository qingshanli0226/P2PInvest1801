package com.example.framwork.mvp.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.ToolBar;
import com.example.framwork.R;
import com.example.framwork.mvp.presenter.IPresenter;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity,IView {
    protected P mPresenter;
    private ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initViews();
        toolBar = findViewById(R.id.toolbar);
        initDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.destroy();
            mPresenter = null;
        }
    }
}
