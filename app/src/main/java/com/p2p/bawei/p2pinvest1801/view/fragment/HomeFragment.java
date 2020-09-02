package com.p2p.bawei.p2pinvest1801.view.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.lib_core.view.BaseFragment;
import com.p2p.bawei.p2pinvest1801.R;
import com.p2p.bawei.p2pinvest1801.bean.MyHomeBean;
import com.p2p.bawei.p2pinvest1801.contract.MyHomeContract;
import com.p2p.bawei.p2pinvest1801.presenter.MyHomePresenter;
import com.p2p.bawei.p2pinvest1801.ProgressView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class HomeFragment extends BaseFragment<MyHomePresenter> implements MyHomeContract.View {


    private Banner banner;
    private ProgressView progressView;
    private List<Integer> list_banner = new ArrayList<>();
    private Button btAdd;
    private List<String> list = null;

    @Override
    public int bandLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        banner = (Banner) findViewById(R.id.banner);
        btAdd = (Button) findViewById(R.id.bt_add);
        initBanner();
        progressView = (ProgressView) findViewById(R.id.progress_view);
        progressView.setProgress(90);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                progressView.setFlagBig(true);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("1111");
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
