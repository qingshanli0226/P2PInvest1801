package com.p2p.bawei.p2pinvest1801;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(1080, 1920);
        imageView.setLayoutParams(layoutParams);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.adr);
        imageView.setImageBitmap(bitmap);
        setContentView(imageView);


        new Handler(){
            @Override
            public void handleMessage(@androidx.annotation.NonNull Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }.sendEmptyMessageDelayed(1,1000);
    }
}
