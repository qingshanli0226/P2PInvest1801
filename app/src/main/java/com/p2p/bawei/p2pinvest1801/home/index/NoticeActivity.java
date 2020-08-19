package com.p2p.bawei.p2pinvest1801.home.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.framework2.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

public class NoticeActivity extends BaseActivity {


    private WebView notice_web;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        notice_web=findViewById(R.id.notice_web);
        initWeb();
    }

    private void initWeb() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        notice_web.loadUrl(url);
        notice_web.setWebViewClient(new WebViewClient());
        notice_web.setWebChromeClient(new WebChromeClient());
        WebSettings settings = notice_web.getSettings();
        settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setSupportZoom(true);//支持缩放，默认为true。
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_notice;
    }


}
