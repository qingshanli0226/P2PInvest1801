package com.p2p.bawei.p2pinvest1801.main.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.common.CacheManager;
import com.example.common.InvestConstant;
import com.example.framework.BaseActivity;
import com.example.net.bean.UpdataBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.p2p.bawei.p2pinvest1801.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {
    private RelativeLayout rlWelcome;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences = CacheManager.getCacheManager().getSharedPreferences();
    private SharedPreferences.Editor editor = CacheManager.getCacheManager().getEditor();
    private String newVersion;//网络请求的version
    private String thisVersion;
    private int progress = 0;

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    launchActivity(MainActivity.class, null);
                    finish();
                    break;
                case 2:
                    //更新版本UI
                    String v = msg.obj.toString();
                    tvWelcomeVersion.setText(v);
                    break;
                case 3:
                    progressDialog.setProgress(progress);
                    break;
            }

        }
    };
    private TextView tvWelcomeVersion;


    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        rlWelcome = findViewById(R.id.rl_welcome);
        tvWelcomeVersion = findViewById(R.id.tv_welcome_version);
        //本地存储的version
        thisVersion = sharedPreferences.getString(InvestConstant.SP_VERSION, "1.1");
        //获取最新版本
        getNewVersion();
        tvWelcomeVersion.setText(thisVersion);

        //启动动画
        setAnimation();
        //判断网络连接
        net();
    }

    private void getNewVersion() {
        OkGo.<String>get(InvestConstant.BASE_RESOURCE_JSON_URL + "P2PInvest/update.json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body();
                        UpdataBean.ResultBean result = new Gson().fromJson(json, UpdataBean.class).getResult();
                        newVersion = result.getVersion();
                        //把请求到的version存到本地
                        editor.putString(InvestConstant.SP_VERSION, newVersion);
                        editor.commit();
                        //请求更新对话框(判断当前版本是否是最新的版本)
                        if (!thisVersion.equals(newVersion)) {
                            //打开对话框
                            initDialog();
                        } else {
                            handler.sendEmptyMessageDelayed(1, 2000);
                        }
                    }
                });

    }


    private void initDialog() {
        //创建构造者
        final AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);

        //添加对话框内容
        builder.setTitle("下载最新版本");
        builder.setMessage("解决一些bug，优化网络请求！");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog = new ProgressDialog(WelcomeActivity.this);
                progressDialog.setMax(100);
                //水平
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                Message message = new Message();
                message.what = 2;
                message.obj = newVersion;
                handler.sendMessage(message);

                //TODO 开启一个服务,获取最新版本

                //模拟定时器
                final Timer timer = new Timer();
                /**
                 * 三个参数
                 * 1.TimerTask任务
                 * 2.几秒后执行
                 * 3.每隔几秒执行一次
                 */
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        progress++;
                        if (progress == 100) {
                            timer.cancel();//取消定时器
                            progressDialog.dismiss();//消失
                            //跳转
                            handler.sendEmptyMessage(1);
                        }
                        handler.sendEmptyMessage(3);
                    }
                }, 1, 30);//1秒后执行
                progressDialog.show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
        //创建对话框
        AlertDialog alertDialog = builder.create();
        //显示对话框
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.RED);
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.RED);
    }

    private void net() {
        //判断网络连接状态
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
//            showMessage("网络已连接");
        } else {
            showMessage("无网络连接");
        }
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//0：完全透明  1：完全不透明
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画的变化率
        rlWelcome.startAnimation(alphaAnimation);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

}