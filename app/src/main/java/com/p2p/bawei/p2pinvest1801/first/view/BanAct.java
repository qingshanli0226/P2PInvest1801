package com.p2p.bawei.p2pinvest1801.first.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.example.baselibrary.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class BanAct extends BaseActivity {
    private WebView mBanWeb;

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        mBanWeb = findViewById(R.id.ban_web);
        mBanWeb.getSettings().setJavaScriptEnabled(true);
        mBanWeb.getSettings().setBuiltInZoomControls(true);
        mBanWeb.getSettings().setSupportZoom(true);
        mBanWeb.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
        mBanWeb.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String web_url = intent.getStringExtra("web_url");
        if (web_url != null) {
            mBanWeb.loadUrl(web_url);
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return false;
    }

    @Override
    public int bandLayout() {
        return R.layout.ban_layout;
    }
}
