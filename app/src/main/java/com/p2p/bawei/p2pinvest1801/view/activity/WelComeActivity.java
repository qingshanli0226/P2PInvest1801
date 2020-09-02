package com.p2p.bawei.p2pinvest1801.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎页
 */
public class WelComeActivity extends AppCompatActivity {

    private ImageView imageWelcome;
    // Handler对象用来给UI_Thread的MessageQueue发送消息
    private Handler mHandler;
    // 线程是否运行判断变量
    boolean isrung = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);

        //获取动态权限
        initPermission();
        //初始化
        initView();
        //进度
        initProgess();
        //渐变
        initAlph();
    }

    //开启一个线程，让alph递减
    private void initAlph() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isrung){
                    try {
                        Thread.sleep(2000);
                        //更新alph的值
                        updateAlph();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        mHandler = new Handler(){
            @Override
            public void handleMessage( Message msg) {
                super.handleMessage(msg);
               //imageWelcome.setAlpha(image_alph);
                //刷新视图
                imageWelcome.invalidate();
            }
        };
    }

    //动画
    private void updateAlph() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.image_alpha);
        imageWelcome.startAnimation(animation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(3000);
        imageWelcome.startAnimation(animation);
        mHandler.sendMessage(mHandler.obtainMessage());
    }

    //检查权限

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.CAMERA,
                    Manifest.permission.INTERNET
            },200);
        }
        return super.checkPermission(permission, pid, uid);
    }

    private void initPermission() {

    }

    private void initView() {
        imageWelcome = (ImageView) findViewById(R.id.image_welcome);
    }

    //进度条
    private void initProgess() {
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
                                    //finish();
                                    Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }, 0, 1000);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //直接跳到主页
                        Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }
}
