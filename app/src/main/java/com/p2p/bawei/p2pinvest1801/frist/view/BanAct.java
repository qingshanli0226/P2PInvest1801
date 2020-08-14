package com.p2p.bawei.p2pinvest1801.frist.view;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

public class BanAct extends BaseActivity {
    private WebView mBanWeb;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

        mBanWeb = (WebView) findViewById(R.id.ban_web);
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
        mBanWeb.loadUrl(web_url);
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
