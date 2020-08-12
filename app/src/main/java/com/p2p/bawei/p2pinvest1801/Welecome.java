package com.p2p.bawei.p2pinvest1801;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.net.BaseObserver;
import com.example.net.P2PApi;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;
import com.p2p.bawei.p2pinvest1801.cache.CacheManager;
import com.p2p.bawei.p2pinvest1801.mvp.view.MainActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//欢迎页
public class Welecome extends AppCompatActivity {
    int handlercount = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    handlercount++;
                    if(handlercount == 3){
                        showlog();
                    }
                    break;
                case 1:
                    count--;
                    welcomeCount.setText("倒计时:"+count+"");
                    break;
            }
        }
    };
    String title;
    private void showlog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("下载最新版本");
        builder.setMessage(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Welecome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private TextView welcomeCount;
    int count = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        welcomeCount = (TextView) findViewById(R.id.welcome_count);
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Welecome.this){
                    CacheManager.getInstance().initInter();
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Welecome.this){
                    Log.i("----", "222");
                    for (int i = 3; i >= 0; i--) {
                        handler.sendEmptyMessage(1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Welecome.this){
                    RetrofitManager.getInstance().getRetrofit()
                            .create(P2PApi.class)
                            .versionlist()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new BaseObserver<VersionBean>() {
                                @Override
                                public void onNext(VersionBean versionBean) {
                                    handler.sendEmptyMessage(0);
                                    title = versionBean.getResult().getDesc();
                                }

                                @Override
                                public void onRequestError(String code, String message) {

                                }
                            });
                }
            }
        }).start();
    }
}
