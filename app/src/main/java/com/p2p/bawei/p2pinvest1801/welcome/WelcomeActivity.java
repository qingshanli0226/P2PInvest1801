package com.p2p.bawei.p2pinvest1801.welcome;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.AppUtils;
import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.activity.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.utils.DownloadUtils;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.home.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.home.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.home.mvp.presenter.HomePresenter;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<HomePresenter> implements HomeContract.IHomeContractView {
    private static final String TAG = "WelcomeActivity";
    private int num = 0;
    private float aFloat = 0f;
    private ImageView welcomeImg;
    private TextView time;
    private int handlerNum = 0;
    private UpDateBean.ResultBean result;

    private UpDateService.MyBinder binder;
    private TimerTask timerTask;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (UpDateService.MyBinder) service;
            binder.startDownload();
            if (binder == null) {
                Log.i(TAG, "onClick: NULL");
            } else {
                Log.i(TAG, "onClick: startDownload");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                handlerNum++;
                if (handlerNum == 3) {
                    setDialog();
                }
                Log.i(TAG, "handleMessage: " + handlerNum);
            }


        }
    };
    private AlertDialog alertDialog;

    @Override
    public void onClick(View v) {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        welcomeImg = findViewById(R.id.welcome_img);
        time = findViewById(R.id.time);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
        }

    }

    @Override
    public void initData() {
        mPresenter = new HomePresenter(new HomeModel(), this);
        mPresenter.getHome();
        mPresenter.getUpDate();
        time();
    }

    //倒计时
    private void time() {
        final Timer timer = new Timer();

        timerTask = new TimerTask() {
            int countDown = 6;

            @Override
            public void run() {
                num++;
                countDown--;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        aFloat += 0.2f;
                        welcomeImg.setAlpha(aFloat);
                        time.setText(countDown + "");
                    }
                });
                if (num >= 6) {
                    handler.sendEmptyMessage(1);
                    timer.cancel();
                    timerTask.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onHomeBean(HomeBean homeBean) {
        Log.i("liuxuan", "WelcomeActivity  onHomeBean: " + homeBean.getCode());
        Log.i("liuxuan", "WelcomeActivity  onHomeBean: " + homeBean.getResult().toString());

        if (homeBean.getCode() == 200) {
            handler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onUpDateBean(UpDateBean upDateBean) {

        if (upDateBean.getCode() == 200) {
            result = upDateBean.getResult();
        }
        //获取 App 版本码
        int appVersionCode = AppUtils.getAppVersionCode();

        if (appVersionCode < result.getVersionCode()) {
            handler.sendEmptyMessage(1);
        }


    }

    private void setDialog() {
        alertDialog = new AlertDialog.Builder(WelcomeActivity.this)
                .setTitle("下载最新版本")
                .setMessage("啊啊")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(WelcomeActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new DownloadUtils(WelcomeActivity.this, "http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk", "abc.apk");
                            }
                        }).start();

                    }
                })
                .show();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        alertDialog.cancel();
        handler.removeCallbacksAndMessages(null);
    }


}
