package com.p2p.bawei.p2pinvest1801.mvp.view;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.p2p.bawei.p2pinvest1801.mvp.presenter.IPresenter;


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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initView();
        initPresenter();
        initData();
    }


    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long l = System.currentTimeMillis();
        long jian = l - thisTime;
        if (jian > 1000) {
            thisTime = l;
            jian = 0;
            showMessage("再次点击退出应用");
        } else if (jian <= 1000) {
            finish();
        }
        return false;
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("hq", "showMessage: " + message);
    }


}
