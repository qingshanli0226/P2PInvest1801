package com.p2p.bawei.p2pinvest1801;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView wImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(wImg, "alpha", 0, 1);
        alpha.setDuration(3000);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);


    }

    private void initView() {
        wImg = findViewById(R.id.w_img);
    }
}
