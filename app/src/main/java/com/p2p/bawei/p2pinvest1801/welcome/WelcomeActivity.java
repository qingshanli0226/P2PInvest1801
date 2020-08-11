package com.p2p.bawei.p2pinvest1801.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private int num = 0;
    private float aFloat = 1.0f;
    private ImageView welcomeImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeImg = findViewById(R.id.welcome_img);


        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                aFloat -= 0.2f;
                welcomeImg.setAlpha(aFloat);
                num++;
                if (num >= 6) {
                    timer.cancel();
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 0, 1000);
    }
}
