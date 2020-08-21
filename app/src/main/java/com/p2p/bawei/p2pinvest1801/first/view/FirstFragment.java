package com.p2p.bawei.p2pinvest1801.first.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baselibrary.mvp.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.FirstBean;
import com.p2p.bawei.p2pinvest1801.first.contract.FirstContract;
import com.p2p.bawei.p2pinvest1801.first.model.FirstModel;
import com.p2p.bawei.p2pinvest1801.first.presenter.FirstPresenter;
import com.example.baselibrary.view.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends BaseFragment<FirstPresenter> implements FirstContract.View {
    private ProgressView mProgressMe;
    private Banner mFirstBanner;
    private List<String> titles = new ArrayList<>();
    private List<FirstBean.ResultBean.ImageArrBean> imageArr;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        mFirstBanner =  findViewById(R.id.first_banner);
        mProgressMe =  findViewById(R.id.progress_me);
        mFirstBanner.setBannerStyle(3);
        titles.add("1");
        titles.add("2");
        titles.add("3");
        titles.add("4");
        mFirstBanner.setBannerTitles(titles);
        mFirstBanner.setBannerAnimation(Transformer.Accordion);
        mFirstBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = imageArr.get(position).getIMAPAURL();
                startActivity(new Intent(getContext(), BanAct.class).putExtra("web_url", url));
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getFirstData();
    }

    @Override
    public void initPresenter() {
        mPresenter = new FirstPresenter(this, new FirstModel());
    }

    @Override
    public int bandLayout() {
        return R.layout.first_layout;
    }

    @Override
    public void upData(FirstBean firstBean) {
        if (imageArr == null) {
            imageArr = new ArrayList<>();
        }
        ((TextView) findViewById(R.id.first_title)).setText(firstBean.getResult().getProInfo().getName());
        imageArr = firstBean.getResult().getImageArr();
        mFirstBanner.setImages(imageArr);
        mFirstBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(((FirstBean.ResultBean.ImageArrBean) path).getIMAURL()).into(imageView);
            }
        }).start();
        mProgressMe.setProgress(Integer.parseInt(firstBean.getResult().getProInfo().getProgress()));
    }
}
