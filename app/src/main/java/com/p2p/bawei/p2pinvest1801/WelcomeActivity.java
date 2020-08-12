package com.p2p.bawei.p2pinvest1801;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private int num = 3;
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

                initDalog();
            }
        }
    };

    @Override
    public void initView() {
        wImg = findViewById(R.id.w_img);
        wTime = findViewById(R.id.w_time);

        mPresenter = new HdPresenter(new HdModel(), this);
        mPresenter.homeData();
        mPresenter.upDate();

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
        HomeDataManager.getInstance().setHomeBean(homeData); //将请求到的首页数据放到单列类中。
    }

    @Override
    public void setUpdateBean(UpdataBean updateBean) {
        result = updateBean.getResult();

    }

    @Override
    public void showMsg(Object msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
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
}
