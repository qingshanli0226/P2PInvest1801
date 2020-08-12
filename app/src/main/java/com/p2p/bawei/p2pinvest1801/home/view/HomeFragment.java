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
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
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
    private List<String> stringList = new ArrayList<>();
    private RoundProgressBar roundProHome;
    private TextView tvRoundPro;
    private Timer timer = new Timer();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                tvRoundPro.setText(index+"%");
            }
        }
    };
    private int index = 0;

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
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        HomeBean.ResultBean result = homeBean.getResult();
        List<HomeBean.ResultBean.ImageArrBean> imageArr = result.getImageArr();

        roundProHome.setProgress(0.9f);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                index++;
                if (index >= 90){
                    timer.cancel();
                }
                handler.sendEmptyMessage(1);
            }
        },0,20);

        for (int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            stringList.add(imaurl);
        }
        banner.setImages(stringList);
        //设置轮播时间
        banner.setDelayTime(1500);
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
