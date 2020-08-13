package com.p2p.bawei.p2pinvest1801;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.common.FinanceConstant;
import com.example.framework.base.BaseMVPActivity;
import com.example.net.mode.BannerBean;
import com.example.net.mode.VersionBean;
import com.p2p.bawei.p2pinvest1801.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseMVPActivity<HomePresenterImpl, HomeContract.HomeView> implements HomeContract.HomeView {
    private ImageView welcomeImg;
    private RelativeLayout relative;
    private TextView timerCount;

    private Timer timer;
    private int count = 3;
    private int handlerCount = 0;

    private VersionBean newVersionBean;
    private BannerBean newBannerBean;

    //banner数据源
    private ArrayList<String> bannerArrayList = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                handlerCount++;
                if(handlerCount == 3){
                    //开启Dialog
                    startDialog();
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

            }
        });
        //取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(FinanceConstant.BUNDLE_BANNER,bannerArrayList);
                bundle.putParcelable("hj",newBannerBean);
                lunachActivity(MainActivity.class,bundle);
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        //显示dialog
        alertDialog.show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        welcomeImg = (ImageView) findViewById(R.id.welcomeImg);
        relative = (RelativeLayout) findViewById(R.id.relative);
        timerCount = (TextView) findViewById(R.id.timerCount);
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
//        printLog(bannerBean.toString());
        List<BannerBean.ResultBean.ImageArrBean> imageArrBeans = bannerBean.getResult().getImageArr();
        for (int i = 0; i <imageArrBeans.size() ; i++) {
            bannerArrayList.add(imageArrBeans.get(i).getIMAURL());
        }
        newBannerBean = bannerBean;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onGetVersionData(VersionBean versionBean) {
//        printLog(versionBean.toString());
        newVersionBean = versionBean;
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
}
