package com.p2p.bawei.p2pinvest1801.welcome.view;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.baselibrary.mvp.view.BaseActivity;
import com.example.net.connecct.NetConnect;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.WelComeUpAppBean;
import com.p2p.bawei.p2pinvest1801.welcome.model.WelComeModel;
import com.p2p.bawei.p2pinvest1801.welcome.presenter.WelComePresenter;
import com.p2p.bawei.p2pinvest1801.welcome.center.WelContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WelComeAct extends BaseActivity<WelComePresenter> implements WelContract.View {
    private TextView mTimeDao;
    private int index = 6;
    private Handler handler = new Handler();

    @Override
    public void onClick(View v) {

    }


    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 25) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
        }
        mTimeDao = findViewById(R.id.time_dao);
        ImageView mWelComeP2PPic = findViewById(R.id.WelCome_P2P_pic);
        Glide.with(this).load(R.drawable.a).transform(new CenterCrop()).into(mWelComeP2PPic);

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
                mPresenter.getData();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void isUpApp(final WelComeUpAppBean.ResultBean str) {
        new AlertDialog.Builder(this)
                .setTitle("是否更新")
                .setMessage(str.getDesc())
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
                        upApp(str.getApkUrl());
                    }

                }).show();
    }


    @Override
    public void initData() {
    }

    private void timeDao() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTimeDao.setText(String.valueOf(index));
                            if (index == 1) {
                                Intent intent = new Intent(WelComeAct.this, MainActivity.class);
                                startActivity(intent);
                                finish();
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
        });
        thread.start();
    }

    private void upApp(final String str) {

        final ProgressDialog progressDialog = new ProgressDialog(WelComeAct.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        Thread hq = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(str);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        int contentLength = httpURLConnection.getContentLength();
                        progressDialog.setMax(contentLength);
                        int progress = progressDialog.getProgress();
                        String path = Environment.getExternalStorageDirectory().getPath();
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(path + "a.apk"));
                        byte[] bytes = new byte[1024];
                        int len;
                        while ((len = inputStream.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0, len);
                            progress += len;
                            progressDialog.setProgress(progress);
                        }
                        timeDao();
                    } else {
                        Log.e("hq", "run: " + responseCode);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        hq.start();

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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void upAppCode(WelComeUpAppBean.ResultBean str) {
        isUpApp(str);
    }

}
