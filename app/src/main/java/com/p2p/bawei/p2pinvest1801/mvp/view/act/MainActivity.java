package com.p2p.bawei.p2pinvest1801.mvp.view.act;


import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.Share;
import com.p2p.bawei.p2pinvest1801.bean.BannerBean;
import com.p2p.bawei.p2pinvest1801.mvp.contact.MainContact;
import com.p2p.bawei.p2pinvest1801.mvp.model.MainModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.MainPre;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity<MainPre> implements MainContact.View {
    private Timer timer;
    private int time=4;
    private float a=1.0f;
    private TextView tt;
    private ImageView iv;
    private MainPre mainPre;
    private int num=0;
    private Message message;

    @Override
    public int banlayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        tt = findViewById(R.id.tt);
        timer=new Timer();
        iv = findViewById(R.id.iv);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Log.i("TAG", "handleMessage: "+time);
               tt.setText("  "+time+"  S");
                if(time<=0){
                    timer.cancel();
//                    num++;
//                    if(num>=1){
                        initdialog();
//                    }
                }
            }
        }
    };
    @Override
    public void initData() {
        mainPre=new MainPre(new MainModel(),this);
        mainPre.list();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                message= new Message();
                message.what=1;
                message.obj=time;
                handler.sendMessage(message);
            }
        },1,1000);

    }

    private void initdialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("下载最新版本")
                .setMessage("123456789")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, SyActivity.class);
                        startActivity(intent);
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    @Override
    public void initBanner(BannerBean bannerBean) {
        for(int i = 0;i < bannerBean.getResult().getImageArr().size();i++){
            Share.list.add(bannerBean.getResult().getImageArr().get(i));
            if(Share.list.size()==4){
//                handler.sendMessage(message);
            }
        }
        Log.i("zcx", "initBanner: "+Share.list.size());
    }
}
