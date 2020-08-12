package com.p2p.bawei.p2pinvest1801.mvp.view.act;


import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private int time=0;
    private float a=1.0f;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	    timer=new Timer();
        iv = findViewById(R.id.iv);
	    timer.schedule(new TimerTask() {
            @Override
            public void run() {
                a-=0.001f;
                time+=1;
                iv.setAlpha(a);
                if(time>3005){
                    Intent intent = new Intent(MainActivity.this, SyActivity.class);
                    startActivity(intent);
                    Log.i("zcx", "run: "+time);
                    Log.i("Zcx", "run1: "+a);
                    timer.cancel();
                }
            }
        },1,1);
    }
}
