package com.p2p.bawei.p2pinvest1801.welcome;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.net.connecct.NetConnect;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class WelComeAct extends BaseActivity<WelComePresenter> implements WelContract.View {
    private ImageView mWelComeP2PPic;
    private boolean first;
    private TextView mTimeDao;
    private Handler handler = new Handler();

    @Override
    public void onClick(View v) {

    }

    int index = 6;

    @Override
    public void initView() {
        mWelComeP2PPic = (ImageView) findViewById(R.id.WelCome_P2P_pic);
        mTimeDao = (TextView) findViewById(R.id.time_dao);

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
                if (first) {

                } else {
                    isUpApp();
//                    SharedPreferences.Editor edit = sp.edit();
//                    edit.putBoolean("first", true);
//                    edit.commit();
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

    private void isUpApp() {
        new AlertDialog.Builder(this)
                .setTitle("是否更新")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timeDao();
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!NetConnect.isNetworkConnected(WelComeAct.this)) {
                            Toast.makeText(WelComeAct.this, "请检查网络状态", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        upApp();
                    }

                }).show();
    }


    @Override
    public void initData() {
            mPresenter.getData();
    }

    private void timeDao() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTimeDao.setText(String.valueOf(index));
                            Log.e("hq", "run: " + String.valueOf(index));
                            if (index == 1) {
                                Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    index--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void upApp() {
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
                    progressDialog.dismiss();
                    finish();
                    Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 0, 100);
    }

    @Override
    public void initPresenter() {
        mPresenter = new WelComePresenter(this, new WelComeModel());
    }


    @Override
    public int bandLayout() {
        return R.layout.activity_wel_come;
    }

    @Override
    public void upAppCode(String str) {
        timeDao();

    }
}
