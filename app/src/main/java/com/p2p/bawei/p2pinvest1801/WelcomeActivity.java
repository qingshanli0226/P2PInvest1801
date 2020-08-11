package com.p2p.bawei.p2pinvest1801;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.framework.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    private ImageView welcomeImg;
    private RelativeLayout relative;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        welcomeImg = (ImageView) findViewById(R.id.welcomeImg);
        relative = (RelativeLayout) findViewById(R.id.relative);

        //去掉窗口标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏顶部状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //开启动画
        startAnimator();
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
                printLog("1");
                lunachActivity(MainActivity.class,null);
                finish();
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
}
