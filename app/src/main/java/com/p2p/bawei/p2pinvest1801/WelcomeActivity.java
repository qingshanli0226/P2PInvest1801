package com.p2p.bawei.p2pinvest1801;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.common.CacheManager;
import com.example.common.FinanceConstant;
import com.example.framework.base.BaseMVPActivity;
import com.example.framework.base.manager.UserManager;
import com.example.framework.base.service.FinanceService;
import com.example.net.mode.BannerBean;
import com.example.net.mode.VersionBean;
import com.p2p.bawei.p2pinvest1801.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseMVPActivity<HomePresenterImpl, HomeContract.HomeView> implements HomeContract.HomeView {
    ImageView welcomeImg;
    private RelativeLayout relative;
    private TextView timerCount;

    private Timer timer;
    private int count = 3;
    private int handlerCount = 0;

    private VersionBean newVersionBean;
    private BannerBean newBannerBean;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;



    //banner数据源
//    private ArrayList<String> bannerArrayList = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                handlerCount++;
                if(handlerCount == 3){
                    if(newVersionBean.getResult().getVersion().equals(sharedPreferences.getString(FinanceConstant.VERSION,""))){
                        //不用更新
                        startActivity();
                    } else{
                        //版本号不一样 需要更新
                        //开启Dialog
                        startDialog();
                    }
                }
            }
        }
    };

    private void startDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("下载最新版本");
        builder.setMessage(newVersionBean.getResult().getDesc());
        //确认
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //更新版本
                updateApkFile();
            }
        });
        //取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity();
            }
        });

        AlertDialog alertDialog = builder.create();
        //显示dialog
        alertDialog.show();
    }

    private void startActivity() {
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList(FinanceConstant.BUNDLE_BANNER, bannerArrayList);
//        bundle.putParcelable("hj", newBannerBean);
        lunachActivity(MainActivity.class, null);
        finish();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        welcomeImg = (ImageView) findViewById(R.id.welcomeImg);
        relative = (RelativeLayout) findViewById(R.id.relative);
        timerCount = (TextView) findViewById(R.id.timerCount);

        sharedPreferences = CacheManager.getInstance().getSharedPreferences();
        editor = CacheManager.getInstance().getEditor();

        timer = new Timer();

        //去掉窗口标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏顶部状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //开启动画
        startAnimator();
        //开启倒计时
        startTimer();

    }

    private void updateApkFile() {
        //判断手机是否有网络
        boolean connect = isConnect();
        if(connect){
            //有网络
            //开启Service下载版本
            UserManager.getInstance().getBinder().getFinanceService().downLoadFile(newVersionBean.getResult().getApkUrl());
            //改变储存的版本信息
        } else{
            //无网络
            showMessage("当前无网络连接，更新失败");
            startActivity();

        }
    }

    private boolean isConnect() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            return networkInfo.isConnected();
        }
        return false;
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(count >= 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timerCount.setText("倒计时"+count+"秒");
                            count--;
                        }
                    });
                } else{
                    timer.cancel();
                    handler.sendEmptyMessage(1);
                }
            }
        },0,1000);
    }

    private void startAnimator() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);//0：完全透明  1：完全不透明
        alpha.setDuration(3000);
        alpha.setInterpolator(new AccelerateInterpolator());
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        relative.startAnimation(alpha);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initGetData() {
        //获取banner数据
        iHttpPresenter.onBannerData();
        //获取version数据
        iHttpPresenter.onVersionData();
    }

    @Override
    protected void initPresenter() {
        iHttpPresenter = new HomePresenterImpl();
    }

    @Override
    public void onGetBannerData(BannerBean bannerBean) {
        UserManager.getInstance().setBannerBean(bannerBean);

//        List<BannerBean.ResultBean.ImageArrBean> imageArrBeans = bannerBean.getResult().getImageArr();
//        for (int i = 0; i <imageArrBeans.size() ; i++) {
//            bannerArrayList.add(imageArrBeans.get(i).getIMAURL());
//        }
        newBannerBean = bannerBean;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onGetVersionData(VersionBean versionBean) {
//        printLog(versionBean.toString());
        newVersionBean = versionBean;
        //设置版本信息
        UserManager.getInstance().setVersion(versionBean.getResult().getVersion());
        //保存版本信息1.1 让第一次可以更新
        editor.putString(FinanceConstant.VERSION,"1.1");
        editor.commit();
        handler.sendEmptyMessage(1);
    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //页面结束时关闭
        timer.cancel();
        //移除handle未处理的消息
        handler.removeCallbacksAndMessages(null);
    }
}
