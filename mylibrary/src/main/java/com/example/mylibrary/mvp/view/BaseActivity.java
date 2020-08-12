package com.example.mylibrary.mvp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.mvp.presenter.IPresenter;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity,IView {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(banlayout());
        initView();
        initData();
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
