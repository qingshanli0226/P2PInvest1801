package com.p2p.bawei.p2pinvest1801.mvp.view;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.LinechartDemoView;
import com.p2p.bawei.p2pinvest1801.mvp.view.mine.MyScollows;

public class TouZiActivity extends BaseActivity {
    private MyScollows touziScollow;
    @Override
    public void initViews() {
        touziScollow = (MyScollows) findViewById(R.id.touzi_scollow);
    }
    @Override
    public void initDatas() {

    }

    @Override
    public int bandLayout() {
        return R.layout.touzi;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void showError(String code, String message) {

    }
}
