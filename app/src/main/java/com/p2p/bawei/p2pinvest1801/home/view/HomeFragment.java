package com.p2p.bawei.p2pinvest1801.home.view;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.common.view.MyLoadingBar;
import com.example.framework.BaseMVPFragment;
import com.example.net.bean.HomeBean;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.home.contract.HomeContract;
import com.p2p.bawei.p2pinvest1801.home.presenter.HomePresenterImpl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView {
    private MyLoadingBar loadingBar;
    private Banner banner;
    //获取banner数据
    private final List<String> stringList = new ArrayList<>();
    private RoundProgressBar roundProHome;
    private TextView tvRoundPro;
    private final Timer timer = new Timer();
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                tvRoundPro.setText(index + "%");
            }
        }
    };
    private int index = 0;
    private TextView tvHomeProduct;
    private TextView tv_home_yearrate;


    @Override
    protected void initHttpData() {
        ihttpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new HomePresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loadingBar = findViewById(R.id.loadingBar);
        banner = findViewById(R.id.banner);
        roundProHome = findViewById(R.id.roundPro_home);
        tvRoundPro = findViewById(R.id.tv_roundPro);
        tvHomeProduct = findViewById(R.id.tv_home_product);
        tv_home_yearrate = findViewById(R.id.tv_home_yearrate);

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();

        final float v = Float.parseFloat(result.getProInfo().getProgress());

        tvHomeProduct.setText(result.getProInfo().getName());
        tv_home_yearrate.setText(result.getProInfo().getYearRate() + "%");

        roundProHome.setProgress(v/100);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                index++;
                if (index >= v) {
                    timer.cancel();
                }
                handler.sendEmptyMessage(1);
            }
        }, 0, 20);

        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            stringList.add(imaurl);
        }
        banner.setImages(stringList);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置标题集合（当banner样式有显示title时）
        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
        banner.setBannerTitles(Arrays.asList(titles));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置页码与标题
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .fitCenter()
                        .into(imageView);
            }
        });
        banner.start();
    }

    @Override
    public void showError(String code, String message) {
        showError(code, message);
    }

    @Override
    public void showLoaing() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }
}
