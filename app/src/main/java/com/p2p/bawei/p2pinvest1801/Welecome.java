package com.p2p.bawei.p2pinvest1801;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
//欢迎页
public class Welecome extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(200, 200);
        imageView.setLayoutParams(layoutParams);
        setContentView(imageView);
        imageView.setImageResource(R.mipmap.ic_launcher);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Welecome.this,MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
