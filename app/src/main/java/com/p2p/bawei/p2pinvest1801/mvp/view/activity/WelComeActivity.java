package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lib_core.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎页面
 */
public class WelComeActivity extends BaseActivity {
    private int i = 0;
    private ImageView welcomeImg;

    private float num = 0.6f;

    @Override
    public void initView() {
        //2秒后跳转主页面
        welcomeImg = (ImageView) findViewById(R.id.welcome_img);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                num+=0.1f;
                //渐变
                welcomeImg.setAlpha(num);
                if (i>=3){
                    Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                    startActivity(intent);
                    timer.cancel();
                    finish();
                }
                i++;
            }
        },0,1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initInJect() {

    }

    @Override
    public int BandLayout() {
        return R.layout.activity_wel_come;
    }
}
