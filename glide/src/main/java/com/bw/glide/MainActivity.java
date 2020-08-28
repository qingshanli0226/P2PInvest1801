package com.bw.glide;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bw.framwork.view.BaseActivity;

public class MainActivity extends BaseActivity {

    private Button NetCache;
    private Button memoryCache;

    @Override
    public void initView() {
        NetCache = findViewById(R.id.NetCache);
        memoryCache = findViewById(R.id.memoryCache);

        NetCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

        memoryCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int code, String message) {

    }
}
