package com.p2p.bawei.p2pinvest1801;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int temp=4;
    private ImageView jb;
    private float f=1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jb = (ImageView) findViewById(R.id.jb);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp--;
                f-=0.2f;
                jb.setAlpha(f);
                if (temp<=0){
                    Intent intent = new Intent(MainActivity.this,MyMainActivity.class);
                    startActivity(intent);
                    timer.cancel();
                }
            }
        },0,1000);
    }
}
