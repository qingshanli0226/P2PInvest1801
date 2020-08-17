package com.p2p.bawei.p2pinvest1801;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.common.HomeDataManager;
import com.bw.framwork.view.BaseActivity;
import com.bw.net.bean.HomeBean;
import com.bw.net.bean.UpdataBean;
import com.p2p.bawei.p2pinvest1801.activity.MainActivity;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HdContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HdModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HdPresenter;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<HdPresenter> implements HdContract.View {

    private UpdataBean.ResultBean result;
    private int num = 1;
    private ImageView wImg;
    private TextView wTime;
    private Timer timer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                wTime.setText("倒计时" + num-- + "秒");
            }
            if (num < 0) {
                timer.cancel();
                if (!HomeDataManager.getInstance().isHaveUpdate()) {
                    mPresenter.upDate();
                }
            }
        }
    };

    @Override
    public void initView() {
        wImg = findViewById(R.id.w_img);
        wTime = findViewById(R.id.w_time);

        mPresenter = new HdPresenter(new HdModel(), this);
        if (!HomeDataManager.getInstance().isHave()) {
            mPresenter.homeData();
        }




        ObjectAnimator alpha = ObjectAnimator.ofFloat(wImg, "alpha", 0, 1);
        alpha.setDuration(2000);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(1);
            }
        }, 0, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public int bandLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setHomeData(HomeBean homeData) {
        HomeDataManager.getInstance().saveHomeBean(homeData); //将请求到的首页数据放到单列类中。

    }

    @Override
    public void setUpdateBean(UpdataBean updateBean) {
        Log.i("wjh", "" + HomeDataManager.getInstance().isHaveUpdate());

        result = updateBean.getResult();
        HomeDataManager.getInstance().saveUpdataBean(updateBean);
        initDalog();

    }

    public void initDalog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setTitle("提示");
        if (result != null) {
            builder.setMessage(result.getDesc());
        }
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ProgressDialog progressDialog = new ProgressDialog(WelcomeActivity.this);
                progressDialog.setMessage("正在更新。。");
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();



                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int progress=0;

                    @Override
                    public void run() {
                        if (progress==100){
                            timer.cancel();
                            progressDialog.dismiss();
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            finish();
                        }else {
                            progressDialog.setProgress(progress++);
                        }

                    }
                },0,100);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
