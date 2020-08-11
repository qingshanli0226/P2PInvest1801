package com.p2p.bawei.p2pinvest1801;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.common.SP;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class WelComeAct extends BaseActivity {
    private ImageView mWelComeP2PPic;
    private ProgressDialog progressDialog;
    private boolean first;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mWelComeP2PPic = (ImageView) findViewById(R.id.WelCome_P2P_pic);
        final SharedPreferences sp = getSharedPreferences("Sp", MODE_PRIVATE);
        Glide.with(this).load(R.drawable.a).transform(new CenterCrop()).into(mWelComeP2PPic);

        first = sp.getBoolean("first", false);

        final ObjectAnimator alpha = ObjectAnimator.ofFloat(mWelComeP2PPic, "alpha", 0, 1);
        alpha.setDuration(2000);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.start();


        alpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("hq", "onAnimationEnd: "+first );
                if (first) {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("first", true);
                    edit.commit();
                    Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    initDia();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void initDia() {
        new AlertDialog.Builder(this)
                .setTitle("是否更新")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog progressDialog = new ProgressDialog(WelComeAct.this);
                        progressDialog.setMax(100);

                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.show();

                        final Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            private int index = 0;

                            @Override
                            public void run() {
                                progressDialog.setProgress(index++);
                                if (progressDialog.getProgress() == 100) {
                                    timer.cancel();
                                    finish();
                                    Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }, 0, 100);
                    }
                }).show();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public int bandLayout() {
        return R.layout.activity_wel_come;
    }
}
