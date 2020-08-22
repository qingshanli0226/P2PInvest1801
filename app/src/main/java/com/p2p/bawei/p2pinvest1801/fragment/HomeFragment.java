package com.p2p.bawei.p2pinvest1801.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.common.view.ToolBar;
import com.example.framework.base.BaseFragment;
import com.example.net.mode.BannerBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.ui.RoundProgress;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment {
    private Banner bannerHome;
    private TextView homeTv;
    private RoundProgress homeRoundProgress;
    private TextView homeYear;
    ImageView homeAnim;
    //是否开启线程
    private boolean isStart = true;

    //banner数据源
    private ArrayList<String> stringArrayList;
    private BannerBean bannerBean;

    private int currentProgress;

    public HomeFragment(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            homeRoundProgress.setMax(100);
            for (int i = 0; i < currentProgress ; i++) {
                homeRoundProgress.setProgress(i+1);
                //重新绘制
                SystemClock.sleep(30);
                homeRoundProgress.postInvalidate();
            }
        }
    });

    @Override
    protected void initData() {
        //设置文本
        homeTv.setText(bannerBean.getResult().getProInfo().getName());
        homeYear.setText("预期年利率 ："+bannerBean.getResult().getProInfo().getYearRate()+"%");
        if(isStart){
            //开启线程
            thread.start();
            isStart = false;
        }

        //添加数据
        String[] bannerTitles = new String[]{"分享砍学费","人脉总动员","想不到你是这样的app","购物节，爱不单行"};
        bannerHome.setBannerTitles(Arrays.asList(bannerTitles));
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);//设置页码与标题
        bannerHome.setImages(stringArrayList);
        bannerHome.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        //设置banner轮播动画
        bannerHome.setBannerAnimation(Transformer.DepthPage);
        //开始轮播
        bannerHome.start();

    }

    @Override
    protected void initView() {
        bannerHome = (Banner) findViewById(R.id.bannerHome);
        homeTv = (TextView) findViewById(R.id.homeTv);
        homeRoundProgress = (RoundProgress) findViewById(R.id.homeRoundProgress);
        homeYear = (TextView) findViewById(R.id.homeYear);
        homeAnim = (ImageView) findViewById(R.id.homeAnim);

        //获取banner数据
        Bundle arguments = getArguments();
        bannerBean = arguments.getParcelable("hj1");
        //赋值
        currentProgress = Integer.parseInt(bannerBean.getResult().getProInfo().getProgress());
//        printLog("123123123123");
//        printLog(bannerBean.getResult().getImageArr().get(0).getIMAURL());
//        printLog(bannerBean.getResult().getImageArr().get(1).getIMAURL());
    }
}
