package com.p2p.bawei.p2pinvest1801;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;
//欢迎页
public class Welecome extends AppCompatActivity {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(Welecome.this,MainActivity.class);
            startActivity(intent);
        }
    };
    private RelativeLayout mainRelative;
    private ImageView welcomeImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mainRelative = (RelativeLayout) findViewById(R.id.main_relative);
        welcomeImage = (ImageView) findViewById(R.id.welcome_image);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        welcomeImage.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
}
