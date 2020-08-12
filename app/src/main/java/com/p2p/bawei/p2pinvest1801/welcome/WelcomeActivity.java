package com.p2p.bawei.p2pinvest1801.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

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
    private TextView time;
    private Handler handler = new Handler();
    private int countDown = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeImg = findViewById(R.id.welcome_img);
        time = findViewById(R.id.time);


        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                aFloat -= 0.2f;
                welcomeImg.setAlpha(aFloat);
                num++;
                countDown--;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(countDown + "");
                    }
                });
                if (num >= 6) {
                    timer.cancel();
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 0, 1000);
    }
}
