package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;
import com.p2p.bawei.p2pinvest1801.view.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<MyHomePresenter> implements MyHomeContract.View {


    private Banner banner;
    private ProgressView progressView;
    private List<Integer> list_banner = new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        banner = (Banner) findViewById(R.id.banner);
        initBanner();
        progressView = (ProgressView) findViewById(R.id.progress_view);
        progressView.setProgress(90);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressView.setFlagBig(true);
            }
        });
    }

    private void initBanner() {

        list_banner.add(R.drawable.banner_1);
        list_banner.add(R.drawable.banner_2);
        list_banner.add(R.drawable.banner_3);

        banner.setImages(list_banner).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter(MyHomeBean myHomeBean) {

    }
}
