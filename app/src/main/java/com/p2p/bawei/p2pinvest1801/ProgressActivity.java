package com.p2p.bawei.p2pinvest1801;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        final ProgressView progressView = findViewById(R.id.progressView);
        progressView.setProgress(60);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressView.setFlagBig(true);
            }
        }, 5000);
    }
}

