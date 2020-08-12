package com.p2p.bawei.p2pinvest1801;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelComeActivity extends BaseActivity {
    private ImageView ivIdActivity;
    private TextView tvIdWel;
    private int theTime = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                Intent intent = new Intent(WelComeActivity.this, Main2Activity.class);
                startActivity(intent);

            }
            if (msg.what == 101){
                tvIdWel.setText(theTime+"");
                theTime--;
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initData() {
        setDongHua();
        setTimer();
    }

    private void setTimer() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (theTime < 1){
                    timer.cancel();
                }else {
                    Message message = new Message();
                    message.what = 101;
                    handler.sendMessage(message);
                }
            }
        },1000,1000);
    }

    private void setDongHua() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//0：完全透明  1：完全不透明
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画的变化率
        ivIdActivity.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void initView() {
        ivIdActivity = findViewById(R.id.iv_id_activity);
        tvIdWel = findViewById(R.id.tv_id_wel);

    }

    @Override
    public int banLayout() {
        return R.layout.activity_main;
    }
}
