package com.p2p.bawei.p2pinvest1801.fragmentone;

import android.widget.Toast;

import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.mvp.view.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class FragmentOne extends BaseFragment {
    private Banner bannerF1;
    private ImageLoader imageLoader;
    private ArrayList<Integer> listPath = new ArrayList<Integer>();
    private ArrayList<String> listTitle = new ArrayList<>();

    @Override
    public int banLayout() {
        return R.layout.fragment_layout_one;
    }

    @Override
    public void initView() {
        bannerF1 = (Banner) findViewById(R.id.banner_f1);
        imageLoader = new ImageLoader();

        listPath.add(R.drawable.aaa);
        listPath.add(R.drawable.bbb);
        listPath.add(R.drawable.ccc);
        listPath.add(R.drawable.jjj);

        listTitle.add("一");
        listTitle.add("二");
        listTitle.add("三");
        listTitle.add("四");
    }

    @Override
    public void initData() {
        //样式
        bannerF1.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        bannerF1.setImageLoader(imageLoader);
        //动画效果
        bannerF1.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        bannerF1.setBannerTitles(listTitle);
        //间隔时间
        bannerF1.setDelayTime(3000);
        //是否自动轮播
        bannerF1.isAutoPlay(true);
        //图片小点的显示位置
        bannerF1.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        bannerF1.setImages(listPath);
        //启动
        bannerF1.start();
        //监听
        bannerF1.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "点击了第"+(position+1)+"张轮播图", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }
}
