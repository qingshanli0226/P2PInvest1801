package com.p2p.bawei.p2pinvest1801.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelComeActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    int index = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        initIntent();


    }

    private void initIntent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(index==0){
                                progess();
                            }
                        }
                    });
                    index--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void progess() {
        new AlertDialog.Builder(this)
                .setTitle("下载更新版本")
                .setMessage("解决一些bug,优化网络请求!")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final ProgressDialog progressDialog = new ProgressDialog(WelComeActivity.this);
                        progressDialog.setMax(100);

                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.show();

                        final Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            private int index = 0;

                            @Override
                            public void run() {
                                progressDialog.setProgress(index++);
                                if (progressDialog.getProgress() == 100) {
                                    timer.cancel();
                                    finish();
                                    Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }, 0, 100);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }).show();


    }
}
