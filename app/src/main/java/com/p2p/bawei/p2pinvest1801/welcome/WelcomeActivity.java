package com.p2p.bawei.p2pinvest1801.welcome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.framwork.mvp.view.BaseActivity;
import com.p2p.bawei.p2pinvest1801.MainActivity;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.bean.UpDateBean;
import com.p2p.bawei.p2pinvest1801.home.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.home.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.home.mvp.presenter.HomePresenter;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity<HomePresenter> implements HomeContract.IHomeContractView {

    private int num = 0;
    private float aFloat = 1.0f;
    private ImageView welcomeImg;
    private TextView time;
    private Handler handler = new Handler();
    private int countDown = 5;
    private HomeBean bean;
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
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);

//                    Bundle bundle = new Bundle();
//                    bundle.putParcelable("home", bean);
//                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, 1000);
    }

    @Override
    public void onHomeBean(HomeBean homeBean) {
        bean = homeBean;
        Log.i("liuxuan", "WelcomeActivity  onHomeBean: "+homeBean.getCode());


    }

    @Override
    public void onUpDateBean(UpDateBean upDateBean) {
        Log.i("liuxuan", "WelcomeActivity  upDateBean: "+upDateBean.getCode());

        UpDateBean.ResultBean result = upDateBean.getResult();

        new AlertDialog.Builder(WelcomeActivity.this)
                .setTitle("下载最新版本")
                .setMessage(result.getDesc())
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(WelcomeActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(WelcomeActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();

    }
}
