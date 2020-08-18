package com.p2p.bawei.p2pinvest1801.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_core.mvp.view.BaseActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.mvp.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.mvp.model.HomeModel;
import com.p2p.bawei.p2pinvest1801.mvp.presenter.HomePresenter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎页面
 */
public class WelComeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {
    private int iiiii = 0;
    private ImageView welcomeImg;
    private TextView welcomeCountdown;
    private float num = 0.6f;
    private int number = 3;//倒计时
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                String a = (String) msg.obj;
                welcomeCountdown.setText("倒计时:"+a);
            }else if (msg.what == 0){
                iiiii++;
                if (iiiii==3){
                    AlertDialog.Builder builder = new AlertDialog.Builder(WelComeActivity.this);
                    builder.setTitle("检测到新的版本是否更新");
                    //确定按钮
                    builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProgressDialog progressDialog = new ProgressDialog(WelComeActivity.this);
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置水平央视
                            progressDialog.setMax(100);
                            progressDialog.setMessage("正在下载");
                            progressDialog.show();
                            Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    });
                    //取消按钮
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    });
                    builder.show();
                }
            }
        }
    };
    private Timer timer;
    @Override
    public void initView() {
        welcomeCountdown = (TextView) findViewById(R.id.welcome_countdown);
        welcomeImg = (ImageView) findViewById(R.id.welcome_img);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.obj = number+"";
                handler.sendMessage(message);
                if (number <= 0){
                    Message message1 = new Message();
                    message1.what = 0;
                    handler.sendMessage(message1);
                }
                num+=0.1f;
                number--;
                //渐变
                welcomeImg.setAlpha(num);
            }
        },0,1000);
    }

    @Override
    public void initData() {
        getVersion();
    }

    @Override
    public void initInJect() {
        //进行数据请求
        mPresenter = new HomePresenter(new HomeModel(),this);
        mPresenter.getData();
    }

    @Override
    public int BandLayout() {
        return R.layout.activity_wel_come;
    }

    //获取服务端版本信息
    private void getVersion(){
        OkGo.<String>get("http://49.233.93.155:8080/ atguigu/json/P2PInvest/update.json").execute(new StringCallback() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                Log.e("WelComeActivity -> getVersion", "onSuccess: "+body );
                Message message1 = new Message();
                message1.what = 0;
                handler.sendMessage(message1);
            }
        });
    }

    @Override
    public void initHomeData(HomeBean homeBean) {
        //将请求到的数据进行存储
        HomeBean.ResultBean result = homeBean.getResult();
        Message message1 = new Message();
        message1.what = 0;
        handler.sendMessage(message1);
    }
}
